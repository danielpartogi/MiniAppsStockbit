package com.apelgigit.data.model.request

data class CryptoRequest(
    var pageNum: Int = 0,
    var limit: Int = 20,
    var tsym: String = "IDR"
)