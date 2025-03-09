package com.popupnews.data

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.popupnews.data.model.ReadableArticle
import kotlinx.serialization.json.Json

val ReadableArticleType = object:NavType<ReadableArticle>(false){
    override fun get(bundle: Bundle, key: String): ReadableArticle? {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            bundle.getParcelable(key, ReadableArticle::class.java)
        }else{
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): ReadableArticle {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: ReadableArticle) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: ReadableArticle): String {
        return Json.encodeToString(value)
    }
}