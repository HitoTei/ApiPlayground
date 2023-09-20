package com.example.apiplayground.model.emoji_hub

import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface EmojiHubRepository {
    suspend fun getRandomEmoji(): EmojiHubResponse
}

class EmojiHubRepositoryImpl @Inject constructor() : EmojiHubRepository {
    override suspend fun getRandomEmoji(): EmojiHubResponse {
        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://emojihub.yurace.pro/")
            addConverterFactory(GsonConverterFactory.create())
        }.build()
        val service = retrofit.create(EmojiHubService::class.java)
        return service.getRandomEmoji().await()
    }
}