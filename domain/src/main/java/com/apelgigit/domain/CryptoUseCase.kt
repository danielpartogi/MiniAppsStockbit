package com.apelgigit.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.apelgigit.commons.ext.Resource
import com.apelgigit.data.model.Crypto
import com.apelgigit.data.model.request.CryptoRequest
import com.apelgigit.data.repository.CryptoRepository

class CryptoUseCase(private val repository: CryptoRepository) {

    fun getCryptoList(cryptoRequest: CryptoRequest): LiveData<Resource<List<Crypto>>> {
        return repository.getCryptoList(cryptoRequest).asLiveData()
    }
}