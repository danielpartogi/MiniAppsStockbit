package com.apelgigit.home

import androidx.lifecycle.*
import com.apelgigit.commons.base.BaseViewModel
import com.apelgigit.commons.ext.Resource
import com.apelgigit.commons.thread.DispatcherProvider
import com.apelgigit.data.model.Crypto
import com.apelgigit.data.model.request.CryptoRequest
import com.apelgigit.data.websocket.Subscribe
import com.apelgigit.data.websocket.response.CryptoWSResponse
import com.apelgigit.domain.CryptoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(dispatcherProvider: DispatcherProvider,
                    private val useCase: CryptoUseCase
): BaseViewModel(dispatcherProvider) {

    val cryptoSubsData = useCase.getAllCryptoSubs().asLiveData()
    var observeValue: MutableLiveData<List<String>> = MutableLiveData()

    var rawWsData = observeValue.switchMap {
        useCase.getWSCryptoData(Subscribe("SubAdd", it)).asLiveData()
    }

    fun observeCryptoWs() = viewModelScope.launch {
        delay(1000)
        observeValue.value = listOf("21~BTC")
    }

    fun testAgain() = viewModelScope.launch {
        delay(5000)
        observeValue.value = listOf("21~BTC", "21~ETH")
    }

}