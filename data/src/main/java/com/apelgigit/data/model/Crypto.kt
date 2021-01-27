package com.apelgigit.data.model

import com.google.gson.annotations.SerializedName

data class Crypto(
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfo = CoinInfo(),
    @SerializedName("DISPLAY")
    val display: DisplayCrypto = DisplayCrypto(),
    @SerializedName("RAW")
    val raw: RawCrypto = RawCrypto()
)