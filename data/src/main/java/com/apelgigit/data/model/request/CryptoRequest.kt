package com.apelgigit.data.model.request

data class CryptoRequest(
    var pageNum: Int = 0,
    var limit: Int = 10,
    var tsym: String = "IDR"
)