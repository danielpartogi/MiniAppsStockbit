package com.apelgigit.data.repository

import android.util.Log
import com.apelgigit.commons.ext.RequestStatus
import com.apelgigit.commons.ext.Resource
import com.apelgigit.data.locale.dao.SubsCryptoDao
import com.apelgigit.data.model.Crypto
import com.apelgigit.data.model.request.CryptoRequest
import com.apelgigit.data.remote.services.CryptoService
import com.apelgigit.data.websocket.Subscribe
import com.apelgigit.data.websocket.response.CryptoWSResponse
import com.apelgigit.data.websocket.services.CryptoWSService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext


interface CryptoRepository {
    fun getCryptoList(cryptoRequest: CryptoRequest): Flow<Resource<List<Crypto>>>
    fun getWSCrypto(subscribe: Subscribe): Flow<CryptoWSResponse>
    fun getAllSubsCrypto(): Flow<List<CryptoWSResponse>>
}

class CryptoRepositoryImpl(
   private val service: CryptoService,
   private val ws: CryptoWSService,
   private val dao: SubsCryptoDao
): CryptoRepository {

    override fun getCryptoList(cryptoRequest: CryptoRequest): Flow<Resource<List<Crypto>>> {
        return flow {
            val response = service.getCryptoData(cryptoRequest.limit, cryptoRequest.pageNum, cryptoRequest.tsym)

            emit(Resource(RequestStatus.SUCCESS, response.data, ""))
        }
    }

    override fun getWSCrypto(subscribe: Subscribe): Flow<CryptoWSResponse> {
        return flow {
            ws.subscribe(subscribe)
            ws.observeResponse().collect { response ->
                Log.d("response:ws", response.toString())
                withContext(Dispatchers.IO) {
                    if (response.symbol != "")
                        dao.insert(response)
                }
                emit(response)
            }

        }
    }


    override fun getAllSubsCrypto(): Flow<List<CryptoWSResponse>> {
        return dao.getAll()
    }

}