package com.example.apiplayground.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun jsonToMap(json: String): Map<String, Any> {
    val gson = Gson()
    val mapType = object : TypeToken<Map<String, Any>>() {}.type
    return gson.fromJson(json, mapType)
}