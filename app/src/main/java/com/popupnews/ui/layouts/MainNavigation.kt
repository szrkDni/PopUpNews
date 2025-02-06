package com.popupnews.ui.layouts

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.popupnews.topics

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.Topic)
    {
        composable<Destinations.Topic> {
            TopicScreen(
                onClick = { topic ->
                    navController.navigate(Destinations.InfiniteSwipe(topic))
                }
            )
        }

        composable<Destinations.InfiniteSwipe> {

            val args = it.toRoute<Destinations.InfiniteSwipe>()

            InfSwipeScreen(args.topic) {
                navController.navigate(Destinations.Topic)
            }
        }
    }
}