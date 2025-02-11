package com.popupnews.utils

import androidx.compose.runtime.staticCompositionLocalOf
import com.popupnews.data.local.NewsViewModel

val LocalNewsViewModel = staticCompositionLocalOf<NewsViewModel> {
    error("No ViewModel provided")
}