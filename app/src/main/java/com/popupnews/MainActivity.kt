package com.popupnews

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.popupnews.R
import androidx.lifecycle.lifecycleScope
import com.popupnews.data.api.ApiClient
import com.popupnews.ui.layouts.MainNavigation
import com.popupnews.ui.theme.PopUpNewsTheme
import com.popupnews.utils.TopicItem
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("MainActivity", "onCreate success")
        Log.i("MainActivity", "my API_KEY is: ${ApiClient.API_KEY}")

        //call api for news
        lifecycleScope.launch {
            try { /*NewsService.call()
            NewsService.articles?.forEach{
                Log.i("MainActivity", it.title)*/
                println("h")
            }
            catch (e: IllegalStateException)  { Log.e("MainActivity", "IllegalStateException caught with the message: ${e.message}") }
            catch (e: Exception)              { Log.e("MainActivity", "General Exception caught with the message: ${e.message}") }

        }

        setContent {
            PopUpNewsTheme {

                MainNavigation()

            }
        }

    }
}

//this list contains all the topics with their utils
val topics : MutableList<TopicItem> = mutableListOf(
    TopicItem("General", R.drawable.general),
    TopicItem("All Topics",R.drawable.alltopic),
    TopicItem("Business",R.drawable.business),
    TopicItem("Pop Culture",R.drawable.entertainment),
    TopicItem("Sport",R.drawable.sport1),
    TopicItem("Health",R.drawable.health),
    TopicItem("Science",R.drawable.science),
    TopicItem("Technology",R.drawable.technology)
)
