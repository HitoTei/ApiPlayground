package com.example.apiplayground.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apiplayground.model.ApiData
import com.example.apiplayground.ui.theme.ApiPlaygroundTheme

@Composable
fun ApiList(
    apiList: List<ApiData>,
    onTileClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(apiList) { api ->
            ApiListItem(
                api,
                modifier = modifier
                    .clickable { onTileClick(api.name) }
                    .fillMaxWidth(),
            )
            Divider()
        }
    }
}

@Composable
fun ApiListItem(api: ApiData, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(text = api.name, style = MaterialTheme.typography.titleSmall)
        Text(text = api.description, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
fun ApiListItemPreview() {
    ApiPlaygroundTheme {
        ApiListItem(api = ApiData("Name", "Description"))
    }
}