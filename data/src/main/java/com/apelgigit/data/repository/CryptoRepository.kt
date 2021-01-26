package com.apelgigit.data.repository

import android.util.Log
import com.apelgigit.commons.ext.RequestStatus
import com.apelgigit.commons.ext.Resource
import com.apelgigit.data.model.Crypto
import com.apelgigit.data.model.request.CryptoRequest
import com.apelgigit.data.remote.services.CryptoService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface CryptoRepository {
    fun getCryptoList(cryptoRequest: CryptoRequest): Flow<Resource<List<Crypto>>>
}

class CryptoRepositoryImpl(
   private val service: CryptoService
): CryptoRepository {

    override fun getCryptoList(cryptoRequest: CryptoRequest): Flow<Resource<List<Crypto>>> {
        return flow {
            val response = service.getCryptoData(cryptoRequest.limit, cryptoRequest.pageNum, cryptoRequest.tsym)
            Log.d("test", response.data.toString())

            emit(Resource(RequestStatus.SUCCESS, response.data, ""))
        }
    }
}