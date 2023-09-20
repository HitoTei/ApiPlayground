package com.example.apiplayground.model.gutendex

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GutendexService {
    @GET("books")
    fun getBookList(
    ): Call<GutendexResponse>
}
