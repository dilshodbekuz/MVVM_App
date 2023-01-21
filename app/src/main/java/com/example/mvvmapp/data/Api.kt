package com.example.mvvmapp.data

import com.example.mvvmapp.homePage.model.Model
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("index.php")
    suspend fun getDriver(): Response<BaseDomen<List<Model>>>

}