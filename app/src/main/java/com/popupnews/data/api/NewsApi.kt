package com.popupnews.data.api

import com.popupnews.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    //Deafult call
    @GET("everything")
    suspend fun searchArticle(

        @Query("q")
        q : String,

        @Query("sortBy")
        sortBy : String,

        @Query("language")
        language : String,

        @Query("pageSize")
        pageSize: String,

        @Query("apiKey")
        apiKey : String = ApiClient.API_KEY

    ) : Response<NewsResponse>

    @GET("top-headlines")
    suspend fun topHeadlines(

        @Query("category")
        category : String = "business",

        @Query("country")
        country : String = "us",

        @Query("pageSize")
        pageSize: String = "20",

        @Query("apiKey")
        apiKey : String = ApiClient.API_KEY

    ) : Response<NewsResponse>



}