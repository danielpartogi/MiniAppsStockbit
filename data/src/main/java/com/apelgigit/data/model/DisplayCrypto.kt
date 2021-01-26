package com.apelgigit.data.model

import com.google.gson.annotations.SerializedName

data class DisplayCrypto(
    @SerializedName("IDR")
    val displayDetail: DisplayCryptoDetail
)