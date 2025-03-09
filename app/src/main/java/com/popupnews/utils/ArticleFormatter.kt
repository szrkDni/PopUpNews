package com.popupnews.utils

import com.popupnews.data.model.Article

fun Article.formatTitle() : String{
    return this.title.split(" - ")[0]
}

fun Article.formatDescription() : String{
    //return this.description.split(Regex("(?<=\\.)\\s+"))[0]
    return this.description
}
