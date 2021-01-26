package com.apelgigit.data.remote.services

import com.apelgigit.commons.constants.APIConstants.QUERY_PARAM_PAGE
import com.apelgigit.commons.constants.APIConstants.QUERY_PARAM_LIMIT
import com.apelgigit.commons.constants.APIConstants.QUERY_PARAM_TSYM
import com.apelgigit.data.model.response.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val CRYPTO_TOP_DATA = "data/top/totaltoptiervolfull"


interface CryptoService {

    @GET(CRYPTO_TOP_DATA)
    fun getCryptoData(
        @Query(QUERY_PARAM_LIMIT) limit: Int,
        @Query(QUERY_PARAM_PAGE) pageNum: Int,
        @Query(QUERY_PARAM_TSYM) tsym: String
    ): CryptoResponse

}