package com.popupnews.data.local

import androidx.activity.ComponentActivity
import com.popupnews.data.model.Article
import com.popupnews.data.repository.NewsRepository
import com.popupnews.utils.RequestParameters

class NewsService : ComponentActivity() {

    companion object{

        private val newsRepository = NewsRepository()

        var articles :MutableList<Article>? = null

        suspend fun callTopHeadlines(category: String)
        {

            //Throws IllegalStateException
            val articles = checkNotNull(
                newsRepository.topHeadlines(category = category)
            )

            {"API call success, but list is null"}


            //Throws IllegalStateException
            check(articles.isNotEmpty()) {"API call success, but list is empty"}

            this.articles = articles
        }

        suspend fun callEverything()
        {
            TODO("Implement")
        }

    }



}