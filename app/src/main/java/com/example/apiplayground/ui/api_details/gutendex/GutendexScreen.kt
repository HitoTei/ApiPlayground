package com.example.apiplayground.ui.api_details.gutendex

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GutendexScreen(
    viewModel: GutendexViewModel = hiltViewModel()
) {
    val response by viewModel.bookResponse.observeAsState()

    LazyColumn {
        item {
            Button(onClick = { viewModel.getBookList() }) {
                Text(text = "Get Book List")
            }
        }
        if (response != null) {
            item {
                Text(text = "Count: ${response!!.count}")
            }
            items(response!!.results){
                Text(text = it.title)
            }
        }
    }
}