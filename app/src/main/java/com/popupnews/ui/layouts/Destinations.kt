package com.popupnews.ui.layouts

import com.popupnews.data.model.ReadableArticle
import kotlinx.serialization.Serializable

sealed class Destinations {

    @Serializable
    data object Topic : Destinations()

    @Serializable
    data class InfiniteSwipe(val category : String) : Destinations()

    @Serializable
    data class ReadArticle(val readable: ReadableArticle, val urlToImg:  String) : Destinations()

}