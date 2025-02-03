package com.popupnews.data.local

import androidx.activity.ComponentActivity
import com.popupnews.data.model.Article
import com.popupnews.data.repository.NewsRepository
import com.popupnews.utils.RequestParameters

class NewsService : ComponentActivity() {

    companion object{

        private val newsRepository = NewsRepository()

        var articles :MutableList<Article>? = null

        suspend fun call()
        {

            //Throws IllegalStateException
            val articles = checkNotNull(
                newsRepository.topHeadlines()
            )

            {"API call success, but list is null"}


            //Throws IllegalStateException
            check(articles.isNotEmpty()) {"API call success, but list is empty"}


            this.articles = articles
    }

    }



}