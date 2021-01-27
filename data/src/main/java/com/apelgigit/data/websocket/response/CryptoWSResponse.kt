package com.apelgigit.data.websocket.response

import com.google.gson.annotations.SerializedName

data class CryptoWSResponse(
    @SerializedName("TYPE")
    val type: Int = 0,
    @SerializedName("SYMBOL")
    val symbol: String = "",
    @SerializedName("TOPTIERFULLVOLUME")
    val topTierFullVolume: Double = 0.0
)