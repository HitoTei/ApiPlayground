package com.example.apiplayground.ui

import androidx.compose.runtime.Composable
import com.example.apiplayground.model.ApiData
import com.example.apiplayground.ui.api_details.emoji_hub.EmojiHubScreen
import com.example.apiplayground.ui.api_details.gutendex.GutendexContent
import com.example.apiplayground.ui.api_details.gutendex.GutendexScreen
import com.example.apiplayground.ui.api_details.random_dogs.RandomDogsScreen

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

object EmojiHub : ApiDestination {
    override val screen = @Composable {
        EmojiHubScreen()
    }
    override val apiData = ApiData(
        name = "Emoji Hub",
        description = "絵文字の一覧を取得できる"
    )
}

object Gutendex : ApiDestination {
    override val screen = @Composable {
        GutendexScreen()
    }

    override val apiData = ApiData(
        name = "Gutendex",
        description = "書籍の一覧を取得できる"
    )
}

val apiDestinationList = listOf(
    RandomDogs,
    EmojiHub,
    Gutendex
)
