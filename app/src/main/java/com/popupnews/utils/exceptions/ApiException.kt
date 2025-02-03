package com.popupnews.utils.exceptions

class ApiException(code : Int, message : String) : Exception("API request failed with code $code : ${message}}") {
}