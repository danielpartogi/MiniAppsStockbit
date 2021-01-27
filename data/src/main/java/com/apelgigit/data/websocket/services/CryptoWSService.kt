package com.apelgigit.data.websocket.services

import com.apelgigit.data.websocket.Subscribe
import com.apelgigit.data.websocket.response.CryptoWSResponse
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface CryptoWSService {

    @Send
    fun subscribe(request: Subscribe): Boolean

    @Receive
    fun observeResponse(): Flow<CryptoWSResponse>

    @Receive
    fun observeConnection(): Flow<CryptoWSResponse>
}