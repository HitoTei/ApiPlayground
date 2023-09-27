package com.example.apiplayground.ui.api_details.emoji_hub

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EmojiHubScreen(
    viewModel: EmojiHubViewModel = hiltViewModel()
) {
    val emojiList by viewModel.emojiList.collectAsState(null)
    val waiting by viewModel.waiting.collectAsState(false)
    if (waiting) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    Column {
        TextButton(onClick = { viewModel.getEmojiList() }) {
            Text("Get Emoji")
        }


        emojiList?.run {
            Text("name: $name")
            Text("category: $category")
            Text("group: $group")
            Text("Unicode")
            unicode.forEach {
                val emoji = viewModel.convertUnicodeToEmoji(it)
                Row {
                    Text("$it : $emoji")
                }
            }
        }
    }
}



