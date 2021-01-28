package com.apelgigit.domain

import com.apelgigit.commons.utils.Resource
import com.apelgigit.data.model.Crypto
import com.apelgigit.data.model.request.CryptoRequest
import com.apelgigit.data.repository.CryptoRepository
import com.apelgigit.data.websocket.Subscribe
import com.apelgigit.data.websocket.response.CryptoWSResponse
import kotlinx.coroutines.flow.Flow

class CryptoUseCase(private val repository: CryptoRepository) {

    fun getCryptoList(cryptoRequest: CryptoRequest): Flow<Resource<List<Crypto>>> {
        return repository.getCryptoList(cryptoRequest)
    }

    fun getWSCryptoData(subscribe: Subscribe): Flow<CryptoWSResponse> {
        return repository.getWSCrypto(subscribe)
    }

    fun getAllCryptoSubs() : Flow<List<CryptoWSResponse>> {
        return repository.getAllSubsCrypto()
    }

    suspend fun setWatchedCrypto(symbol: String) {
        repository.setSubsCrypto(symbol)
    }

    suspend fun deleteWatchedCrypto(symbol: String){
        repository.deleteSubsCrypto(symbol)
    }
}