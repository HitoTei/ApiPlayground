package com.example.apiplayground.model.gutendex

import com.google.gson.annotations.SerializedName

data class GutendexResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<GutendexResult>
)

data class GutendexResult(
    val id: Int,
    val title: String,
    val subjects: List<String>,
    val formats: Map<String, String>,
    @SerializedName("download_count") val downloadCount: Int,
)
