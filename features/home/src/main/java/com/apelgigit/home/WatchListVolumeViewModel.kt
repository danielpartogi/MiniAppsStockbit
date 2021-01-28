package com.apelgigit.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.apelgigit.commons.base.BaseViewModel
import com.apelgigit.commons.thread.DispatcherProvider
import com.apelgigit.data.websocket.Subscribe
import com.apelgigit.domain.CryptoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WatchListVolumeViewModel(
    dispatcherProvider: DispatcherProvider,
    private val useCase: CryptoUseCase
) : BaseViewModel(dispatcherProvider) {

    val cryptoSubsData = useCase.getAllCryptoSubs().asLiveData()
    private val observeValue: MutableLiveData<List<String>> = MutableLiveData()

    var rawWsData = observeValue.switchMap {
        useCase.getWSCryptoData(Subscribe("SubAdd", it)).asLiveData()
    }

    fun observeWS() = viewModelScope.launch {
        delay(1000)
        useCase.getAllCryptoSubs().collect {
            if ((observeValue.value?.count() ?: 0) != it.count()) {
                observeValue.value = it.map { data -> "21~${data.symbol}" }
            }
        }
    }

    fun deleteWatchList(symbol: String) = viewModelScope.launch {
        useCase.deleteWatchedCrypto(symbol)
    }

}