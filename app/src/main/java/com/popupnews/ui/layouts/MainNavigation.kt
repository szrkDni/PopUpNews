package com.popupnews.ui.layouts

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.popupnews.data.local.NewsViewModel
import com.popupnews.ui.ReadArticleScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.Topic)
    {

        composable<Destinations.Topic> {
            TopicScreen(
                onClick = { category ->
                    navController.navigate(Destinations.InfiniteSwipe(category))
                }
            )
        }

        composable<Destinations.InfiniteSwipe> {

            val args = it.toRoute<Destinations.InfiniteSwipe>()


            InfSwipeScreen(
                category = args.category,
                onBackClick = { navController.navigate(Destinations.Topic) },
                onArticleClick = { navController.navigate(Destinations.ReadArticle) }
            )

        }

        composable<Destinations.ReadArticle> {
            ReadArticleScreen(
                onBackClick = { navController.navigate(Destinations.Topic)}
            )
        }


    }
}