package com.apelgigit.commons.ext

data class Resource<out T>(val requestStatus: RequestStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                RequestStatus.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(
                RequestStatus.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                RequestStatus.LOADING,
                data,
                null
            )
        }

        fun <T> empty(data: T?): Resource<T> {
            return Resource(
                RequestStatus.EMPTY,
                data,
                null
            )
        }
    }
}

enum class RequestStatus {
    SUCCESS,
    ERROR,
    LOADING,
    EMPTY
}