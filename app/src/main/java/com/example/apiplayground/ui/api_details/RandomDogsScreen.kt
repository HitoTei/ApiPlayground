package com.example.apiplayground.ui.api_details

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.apiplayground.utils.jsonToMap
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

@Composable
fun RandomDogsScreen() {
    var dogMediaUrl by remember {
        mutableStateOf<String?>(null)
    }

    val context = LocalContext.current

    Column {
        TextButton(
            onClick = {
                val request = Request.Builder()
                    .url("https://random.dog/woof.json?include=jpg,png,jpeg,gif")
                    .build()
                val client = OkHttpClient()
                client.newCall(request)
                    .enqueue(object : Callback {
                        override fun onResponse(call: Call, response: Response) {
                            try {
                                val map = jsonToMap(response.body?.string() ?: return)
                                dogMediaUrl = map["url"] as String
                            } catch (e: Exception) {
                                Log.d("RandomDogsScreen", "onResponse: $e")
                            }
                        }

                        override fun onFailure(call: Call, e: IOException) = Unit
                    })
                Toast.makeText(context, "画像を取得します！", Toast.LENGTH_SHORT).show()
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
                modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxSize()
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
