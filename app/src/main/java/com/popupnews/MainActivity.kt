package com.popupnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.popupnews.R
import com.popupnews.ui.layouts.MainNavigation
import com.popupnews.ui.theme.PopUpNewsTheme
import com.popupnews.data.model.TopicItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PopUpNewsTheme {

                MainNavigation()

            }
        }

    }
}

//this list contains all the topics with their utils
val topics : MutableList<TopicItem> = mutableListOf(
    TopicItem("General", "general",R.drawable.general),
    TopicItem("All Topics","",R.drawable.alltopic),
    TopicItem("Business","business",R.drawable.business),
    TopicItem("Pop Culture","entertainment",R.drawable.entertainment),
    TopicItem("Sport","sports",R.drawable.sport1),
    TopicItem("Health","health",R.drawable.health),
    TopicItem("Science","science",R.drawable.science),
    TopicItem("Technology","technology",R.drawable.technology)

)
