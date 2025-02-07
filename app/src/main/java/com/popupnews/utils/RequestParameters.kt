package com.popupnews.utils

object RequestParameters {

    //for top-headlines endpoint only
    public var category : String = "business"



    //for everything endpoint only
    var sortBy : String = "publishedAt"
    var searchOption : String = "bitcoin"



    //for both
    var language : String = "en"
    var pageSize : String = "20"
}