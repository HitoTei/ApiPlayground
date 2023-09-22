package com.example.apiplayground.model.gutendex

import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface GutendexRepository {
    suspend fun getBookList(
        search: String? = null,
    ): GutendexResponse

    suspend fun getBookListFromUrl(url: String): GutendexResponse
}

class GutendexRepositoryImpl @Inject constructor() : GutendexRepository {

    private fun createService(): GutendexService {
        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://gutendex.com/")
            addConverterFactory(GsonConverterFactory.create())
        }.build()
        return retrofit.create(GutendexService::class.java)
    }

    override suspend fun getBookList(
        search: String?
    ): GutendexResponse {
        val service = createService()
        return service.getBookList(search).await()
    }

    override suspend fun getBookListFromUrl(url: String): GutendexResponse {
        val service = createService()
        return service.getBookListFromUrl(url).await()
    }
}
