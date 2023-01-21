package com.example.mvvmapp.unit

sealed class NetworkingStatus<T> {
    class LOADING<T> : NetworkingStatus<T>()
    class SUCCESS<T>(val data : T) : NetworkingStatus<T>()
    class ERROR<T>(val data : Any) : NetworkingStatus<T>()

}