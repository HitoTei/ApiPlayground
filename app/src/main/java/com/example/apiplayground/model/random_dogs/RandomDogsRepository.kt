package com.example.apiplayground.model.random_dogs

import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface RandomDogsRepository {
    suspend fun getRandomDogsUrl(
        extensions: List<String>
    ): String?
}

class RandomDogsRepositoryImpl @Inject constructor() : RandomDogsRepository {
    override suspend fun getRandomDogsUrl(
        extensions: List<String>
    ): String? {
        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://random.dog/")
            addConverterFactory(GsonConverterFactory.create())
        }.build()
        val service = retrofit.create(RandomDogsService::class.java)
        val response = service.getRandomDogs(extensions.joinToString(",")).await()
        return response.url
    }
}
