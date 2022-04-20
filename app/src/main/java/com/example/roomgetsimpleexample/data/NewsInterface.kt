package com.example.roomget.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=en&apiKey=API_KEY
//6c09a928335c4c689e6d9d1dad9d353f

const val BASE_URL = "https://newsapi.org/"
const val API_KEY  = "6c09a928335c4c689e6d9d1dad9d353f"

interface NewsInterface {
    @GET("v2/top-headlines?country=us&apiKey=$API_KEY")
    fun getHeadLines( @Query("page") page:Int):Call<News>
}

object NewService {
    val newInstance:NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newInstance = retrofit.create(NewsInterface::class.java)
    }
}