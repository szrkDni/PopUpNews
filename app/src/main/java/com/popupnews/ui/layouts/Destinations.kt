package com.popupnews.ui.layouts

import com.popupnews.utils.TopicItem
import kotlinx.serialization.Serializable

sealed class Destinations {

    @Serializable
    data object Topic : Destinations()

    @Serializable
    data class InfiniteSwipe(val category : String) : Destinations()

}