package com.example.apiplayground.model.gutendex

data class GutendexResponse(
    val count: Int,
    val results: List<GutendexResult>
)

data class GutendexResult(
    val id: Int,
    val title: String,
    val subjects: List<String>,
    val formats: Map<String, String>,
    val download_count: Int,
)
