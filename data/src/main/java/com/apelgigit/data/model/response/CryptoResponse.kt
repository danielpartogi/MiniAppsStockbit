package com.apelgigit.data.model.response

import com.apelgigit.data.model.Crypto
import com.google.gson.annotations.SerializedName

data class CryptoResponse(
    @SerializedName("Data")
    val data: List<Crypto>
): BaseResponse()