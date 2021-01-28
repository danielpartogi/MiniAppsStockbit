package com.apelgigit.data.repository

import android.util.Log
import com.apelgigit.commons.utils.RequestStatus
import com.apelgigit.commons.utils.Resource
import com.apelgigit.data.locale.dao.SubsCryptoDao
import com.apelgigit.data.model.Crypto
import com.apelgigit.data.model.request.CryptoRequest
import com.apelgigit.data.remote.services.CryptoService
import com.apelgigit.data.websocket.Subscribe
import com.apelgigit.data.websocket.response.CryptoWSResponse
import com.apelgigit.data.websocket.services.CryptoWSService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext


interface CryptoRepository {
    fun getCryptoList(cryptoRequest: CryptoRequest): Flow<Resource<List<Crypto>>>
    fun getWSCrypto(subscribe: Subscribe): Flow<CryptoWSResponse>
    fun getAllSubsCrypto(): Flow<List<CryptoWSResponse>>
    suspend fun setSubsCrypto(symbol: String)
    suspend fun deleteSubsCrypto(symbol: String)
}

class CryptoRepositoryImpl(
   private val service: CryptoService,
   private val ws: CryptoWSService,
   private val dao: SubsCryptoDao
): CryptoRepository {

    @ExperimentalCoroutinesApi
    override fun getCryptoList(cryptoRequest: CryptoRequest): Flow<Resource<List<Crypto>>> {
        return flow {
            emit(Resource(RequestStatus.LOADING, listOf(), ""))
            val response = service.getCryptoData(cryptoRequest.limit, cryptoRequest.pageNum, cryptoRequest.tsym)

            emit(Resource(RequestStatus.SUCCESS, response.data, ""))
        }.catch {
            emit(Resource.error(it.message ?: "Error", null))
        }
    }

    @ExperimentalCoroutinesApi
    override fun getWSCrypto(subscribe: Subscribe): Flow<CryptoWSResponse> {
        return flow {
            ws.subscribe(subscribe)
            ws.observeResponse().collect { response ->
                withContext(Dispatchers.IO) {
                    if (response.symbol.isNotEmpty())
                        dao.update(response)
                }
                emit(response)
            }
        }.catch {
            Log.e("unhandled_error", it.message.toString())
        }
    }


    override fun getAllSubsCrypto(): Flow<List<CryptoWSResponse>> {
        return dao.getAll()
    }

    override suspend fun setSubsCrypto(symbol: String) {
        withContext(Dispatchers.IO){
           dao.insert(CryptoWSResponse(21, symbol, 0.0))
        }
    }

    override suspend fun deleteSubsCrypto(symbol: String) {
        withContext(Dispatchers.IO){
            dao.remove(symbol)
        }
    }

}