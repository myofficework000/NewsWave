package com.example.newswithcleancode.utils


sealed class ApiResult<T>{
    class Pending<T>: ApiResult<T>()
    data class Success<T>(val body: T): ApiResult<T>()
    data class Failure<T>(val error: String): ApiResult<T>()
}
