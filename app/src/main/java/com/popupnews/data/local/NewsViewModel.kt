package com.popupnews.data.local

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popupnews.data.model.Article
import com.popupnews.data.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    private val newsRepository = NewsRepository()

    var articles = mutableStateListOf<Article>()

    private suspend fun fetchTopHeadlines(category: String) {

        //Throws IllegalStateException
        val articles = checkNotNull(
            newsRepository.topHeadlines(category = category)
        )
        {"API call success, but list is null"}


        //Throws IllegalStateException
        check(articles.isNotEmpty()) {"API call success, but list is empty"}


        this.articles.addAll(articles)
    }

    private suspend fun callEverything() {
        TODO("Implement")
    }

    fun loadArticles(category: String) {
        articles.clear()

        try{
            viewModelScope.launch{
                fetchTopHeadlines(category)
            }

        }
        catch (e: IllegalStateException)  { Log.e("API", "IllegalStateException caught with the message: ${e.message}") }
        catch (e: Exception)              { Log.e("API", "General Exception caught with the message: ${e.message}") }
    }



}