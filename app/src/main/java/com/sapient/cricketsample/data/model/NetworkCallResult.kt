package com.sapient.cricketsample.data.model

sealed class NetworkCallResult<out T> {
    data class Success<out T>(val value: T) : NetworkCallResult<T>()
    data class Error(val throwable: Throwable) : NetworkCallResult<Nothing>()
}