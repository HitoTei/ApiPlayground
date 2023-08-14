package com.example.apiplayground.ui

import androidx.compose.runtime.Composable
import com.example.apiplayground.model.ApiData
import com.example.apiplayground.ui.api_details.RandomDogsScreen

interface ApiDestination {
    val screen: @Composable () -> Unit
    val apiData: ApiData
}

object RandomDogs : ApiDestination {
    override val screen = @Composable {
        RandomDogsScreen()
    }
    override val apiData = ApiData(
        name = "Random Dogs",
        description = "犬の画像や動画をランダムに取得できる\uD83D\uDC36"
    )
}

val apiDestinationList = listOf(
    RandomDogs,
)
