package com.popupnews.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    var title: String,
    val url: String,
    val urlToImage: String
)