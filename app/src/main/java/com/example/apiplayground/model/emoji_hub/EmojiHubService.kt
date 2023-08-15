package com.example.apiplayground.model.emoji_hub

import retrofit2.Call
import retrofit2.http.GET

interface EmojiHubService {
    @GET("api/random")
    fun getRandomEmoji(): Call<EmojiHubResponse>
}