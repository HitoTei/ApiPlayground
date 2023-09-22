package com.example.apiplayground.model.gutendex

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GutendexService {
    @GET("books")
    fun getBookList(
        @Query("search") search: String? = null,
    ): Call<GutendexResponse>

    @GET
    fun getBookListFromUrl(@Url url: String): Call<GutendexResponse>
}
