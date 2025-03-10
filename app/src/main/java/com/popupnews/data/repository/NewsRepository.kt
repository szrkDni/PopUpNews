package com.popupnews.data.repository

import android.util.Log
import com.popupnews.data.api.ApiClient
import com.popupnews.data.model.Article
import com.popupnews.utils.RequestParameters
import com.popupnews.utils.exceptions.ApiException
import retrofit2.Retrofit


/**
 * Data center class
 * Here's the functions to search for articles etc.
 *
 * */
class NewsRepository{

    private val api = ApiClient.api

    suspend fun everything(q: String, sortBy: String, language: String, pageSize: String) : MutableList<Article>
    {

        val response = api.searchArticle(q, sortBy, language, pageSize)


        //Throws IllegalStateException
        check(response.isSuccessful) {"API call failed with code: ${response.code()}, ${response.message()}"}

        return response.body()?.articles ?: mutableListOf()

    }

    suspend fun topHeadlines(
        category: String,
        pageSize: String = RequestParameters.pageSize,
        language: String = RequestParameters.language,

    ) : MutableList<Article>
    {
        val response = api.topHeadlines(
            category = category,
            pageSize = pageSize,
            language = language
        )

        //Throws IllegalStateException
        check(response.isSuccessful) {"API call failed with code: ${response.code()}, ${response.message()}"}

        return response.body()?.articles ?: mutableListOf()
    }

}