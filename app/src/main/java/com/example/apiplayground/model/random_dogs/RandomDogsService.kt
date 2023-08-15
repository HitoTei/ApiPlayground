package com.example.apiplayground.model.random_dogs

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomDogsService {
    @GET("woof.json")
    fun getRandomDogs(
        @Query("include") include: String
    ): Call<RandomDogsResponse>
}
