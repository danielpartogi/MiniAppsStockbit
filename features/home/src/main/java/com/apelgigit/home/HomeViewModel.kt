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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(dispatcherProvider: DispatcherProvider,
                    private val useCase: CryptoUseCase
): BaseViewModel(dispatcherProvider) {

    val foo = useCase.getAllCryptoSubs().asLiveData()

    val test = useCase.getWSCryptoData(Subscribe("SubAdd", listOf("21~BTC"))).asLiveData()

}