package com.popupnews.ui.layouts

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

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

            InfSwipeScreen(args.category) {
                navController.navigate(Destinations.Topic)
            }
        }
    }
}