package com.apelgigit.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.apelgigit.commons.base.BaseViewModel
import com.apelgigit.commons.ext.Resource
import com.apelgigit.commons.thread.DispatcherProvider
import com.apelgigit.data.model.Crypto
import com.apelgigit.data.model.request.CryptoRequest
import com.apelgigit.domain.CryptoUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WatchListViewModel(dispatcherProvider: DispatcherProvider,
                    private val useCase: CryptoUseCase
): BaseViewModel(dispatcherProvider) {

    private val _cryptoDataList: MutableLiveData<Resource<List<Crypto>>> =
        MutableLiveData<Resource<List<Crypto>>>()
    val cryptoDataList: LiveData<Resource<List<Crypto>>>
        get() = _cryptoDataList

    fun fetchData(page: Int) = viewModelScope.launch {

        if (page == 0) {
            _cryptoDataList.value = Resource.empty(listOf())
        }

        useCase.getCryptoList(CryptoRequest(page)).collect { resource ->
            _cryptoDataList.value?.let { data ->
                resource.data?.let {
                    val latestData = data.data?.toMutableList()
                    latestData?.addAll(it)
                    val new = data.copy(
                        data = latestData,
                        requestStatus = resource.requestStatus,
                        message = resource.message
                    )
                    _cryptoDataList.value = new
                }
            }?:run {
                _cryptoDataList.value = resource
            }
        }
    }

    fun setWatchList(symbol: String) = viewModelScope.launch {
        useCase.setWatchedCrypto(symbol)
    }

    fun deleteWatchList(symbol: String) = viewModelScope.launch {
        useCase.deleteWatchedCrypto(symbol)
    }
}