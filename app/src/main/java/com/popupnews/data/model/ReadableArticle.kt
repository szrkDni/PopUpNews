package com.popupnews.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ReadableArticle(
    val author: String,
    val content: String,
    val publishedAt: String,
    var title: String
): Parcelable