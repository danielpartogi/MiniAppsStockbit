package com.apelgigit.data.model.response

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("Type")
    var type: Int = 0,
    @SerializedName("Message")
    var message: String = "",
    @SerializedName("HasWarning")
    var hasWarning: Boolean = false
)