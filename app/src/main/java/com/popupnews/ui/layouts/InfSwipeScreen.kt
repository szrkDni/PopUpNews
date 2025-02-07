package com.popupnews.ui.layouts

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.popupnews.data.local.NewsService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.IllegalStateException

@Composable
fun InfSwipeScreen(category: String, onClick : () -> Unit) {

    runBlocking {
        //call api for news

        try {
            NewsService.callTopHeadlines(category = category)

            NewsService.articles?.forEach {
                Log.i("API", it.title)
            }
        }
        catch (e: IllegalStateException)  { Log.e("API", "IllegalStateException caught with the message: ${e.message}") }
        catch (e: Exception)              { Log.e("API", "General Exception caught with the message: ${e.message}") }


    }

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        NewsService.articles?.let {
            items(it.toList()) {
                Text(
                    text = it.title,
                    color = Color.White,
                    modifier = Modifier.clickable { onClick.invoke() }
                )
            }
        }


    }
}