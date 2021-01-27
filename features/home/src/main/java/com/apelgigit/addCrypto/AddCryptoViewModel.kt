package com.apelgigit.addCrypto

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

class AddCryptoViewModel(dispatcherProvider: DispatcherProvider,
                         private val useCase: CryptoUseCase
): BaseViewModel(dispatcherProvider) {

    var requestedPage: Int = 0

    private val _foo: MutableLiveData<Resource<List<Crypto>>> = MutableLiveData<Resource<List<Crypto>>>()
    val foo: LiveData<Resource<List<Crypto>>>
        get() = _foo


    fun getMore() = viewModelScope.launch {
        useCase.getCryptoList(CryptoRequest(requestedPage)).collect {
            _foo.value?.apply {
                it.data?.let {
                    val latestData = this.data?.toMutableList()
                    latestData?.addAll(it)
                    val new = this.copy(data = latestData)
                    _foo.value = new
                }
            }?:run {
                _foo.value = it
            }
        }
    }
}