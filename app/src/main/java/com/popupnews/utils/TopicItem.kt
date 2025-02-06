package com.popupnews.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class TopicItem(
    val topic : String, val imageRes : Int
):Parcelable
