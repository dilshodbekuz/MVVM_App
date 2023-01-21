package com.example.mvvmapp.data

data class BaseDomen<T>(
    val data: T,
    val success: String,
    val error_code: Int,
){
    companion object{
        const val SUCCESS = 200
        const val UnknownError = 500
    }
}
