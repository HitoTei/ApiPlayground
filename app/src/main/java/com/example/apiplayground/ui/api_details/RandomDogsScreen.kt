package com.example.apiplayground.ui.api_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

@Composable
fun RandomDogsScreen(viewModel: RandomDogsViewModel = hiltViewModel()) {
    val dogMediaUrl by viewModel.dogMediaUrl.observeAsState()


    Column {
        TextButton(
            onClick = {
                viewModel.getDogMediaUrl()
            }
        ) {
            Text(text = "Get Random Dogs!")
        }
        if (dogMediaUrl == null) {
            Text(text = "犬がいない😢")
        } else {
            Text(text = dogMediaUrl ?: "urlが無い")
            DogMedia(
                url = dogMediaUrl!!,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun DogMedia(url: String, modifier: Modifier = Modifier) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (url.endsWith(".gif")) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    AsyncImage(
        model = url,
        imageLoader = imageLoader,
        contentDescription = null,
        modifier = modifier
    )
}
