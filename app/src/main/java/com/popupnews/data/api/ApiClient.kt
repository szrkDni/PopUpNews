package com.popupnews.data.api

import com.example.popupnews.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{

        private const val BASE_URL = "https://newsapi.org/v2/"

        const val API_KEY = "0479b33875c14a4c8f0c3bd7d03db022"

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api by lazy {
            retrofit.create(NewsApi::class.java)
        }
    }

}

