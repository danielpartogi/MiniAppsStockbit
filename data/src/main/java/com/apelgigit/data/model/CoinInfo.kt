package com.apelgigit.data.model

import com.google.gson.annotations.SerializedName

data class CoinInfo(
    @SerializedName("Algorithm")
    val algorithm: String = "",
    @SerializedName("BlockNumber")
    val blockNumber: Int = 0,
    @SerializedName("BlockReward")
    val blockReward: Double = 0.0,
    @SerializedName("BlockTime")
    val blockTime: Double = 0.0,
    @SerializedName("DocumentType")
    val documentType: String = "",
    @SerializedName("FullName")
    val fullName: String = "",
    @SerializedName("Id")
    val id: String = "",
    @SerializedName("ImageUrl")
    val imageUrl: String = "",
    @SerializedName("Internal")
    val internal: String = "",
    @SerializedName("Name")
    val name: String = "",
    @SerializedName("NetHashesPerSecond")
    val netHashesPerSecond: Double = 0.0,
    @SerializedName("ProofType")
    val proofType: String = "",
    @SerializedName("Rating")
    val rating: Rating,
    @SerializedName("Type")
    val type: Int = 0,
    @SerializedName("Url")
    val url: String = ""
)