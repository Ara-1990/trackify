package com.the.trackify.data.apiservise

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }
    val api: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}