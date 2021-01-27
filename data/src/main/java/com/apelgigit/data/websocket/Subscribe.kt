package com.apelgigit.data.websocket

import com.google.gson.annotations.SerializedName

data class Subscribe(
    @SerializedName("action")
    var action: String,
    @SerializedName("subs")
    var subs: List<String>
)