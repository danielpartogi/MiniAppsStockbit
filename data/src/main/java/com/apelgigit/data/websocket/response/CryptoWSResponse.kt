package com.apelgigit.data.websocket.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "crypto_subs")
data class CryptoWSResponse(
    @SerializedName("TYPE")
    val type: Int = 0,
    @PrimaryKey
    @SerializedName("SYMBOL")
    val symbol: String = "",
    @SerializedName("TOPTIERFULLVOLUME")
    val topTierFullVolume: Double = 0.0
)