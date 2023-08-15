package com.example.apiplayground.model.emoji_hub

data class EmojiHubResponse(
    val name: String,
    val category: String,
    val group: String,
    val unicode: List<String>
)
