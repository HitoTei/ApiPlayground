package com.example.apiplayground.ui.api_details.gutendex

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apiplayground.model.gutendex.GutendexResponse
import com.example.apiplayground.model.gutendex.GutendexResult
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GutendexScreen(
    modifier: Modifier = Modifier,
    viewModel: GutendexViewModel = hiltViewModel(),
) {
    val response by viewModel.bookResponse.collectAsState(null)
    val search by viewModel.searchString.collectAsState("")
    val waiting by viewModel.waiting.collectAsState(false)
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                )
            }
        }
    ) {

        if (waiting) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        GutendexContent(
            modifier = Modifier.padding(it),
            response = response,
            search = search,
            getBookList = { viewModel.getBookList(search) },
            getBookListFromUrl = viewModel::getBookListFromUrl,
            onSearchStringChange = viewModel::onSearchStringChange,
            openBookUrl = viewModel::openBookUrl,
            lazyListState = lazyListState,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GutendexContent(
    response: GutendexResponse?,
    search: String,
    getBookList: () -> Unit,
    getBookListFromUrl: (String) -> Unit,
    onSearchStringChange: (String) -> Unit,
    openBookUrl: (String, Context) -> Unit,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    OutlinedButton(
                        onClick = { getBookList() },
                        shape = RectangleShape,
                    ) {
                        Text(text = "Get Book List")
                    }
                    TextField(
                        value = search,
                        onValueChange = {
                            onSearchStringChange(it)
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                        ),
                        label = { Text(text = "Search") },
                        singleLine = true,
                        modifier = Modifier.width(200.dp)
                    )
                    response?.count?.let {
                        Text(text = "Count: $it")
                    }
                }

                Row {
                    response?.previous?.let {
                        TextButton(
                            onClick = { getBookListFromUrl(it) },
                            contentPadding = PaddingValues(0.dp),
                        ) {
                            Text(text = "<< Previous")
                        }
                    }
                    response?.next?.let {
                        TextButton(
                            onClick = { getBookListFromUrl(it) },
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(text = "Next >>")
                        }
                    }
                }
            }
        }
        response?.run {
            items(results) {
                GutendexItem(title = it.title,
                    subjects = it.subjects,
                    formats = it.formats,
                    downloadCount = it.downloadCount,
                    onClickFormat = { url ->
                        openBookUrl(url, context)
                    })
                Divider()
            }
        }
    }
}

@Composable
fun GutendexItem(
    title: String,
    subjects: List<String>,
    formats: Map<String, String>,
    downloadCount: Int,
    onClickFormat: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = title, style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = subjects.joinToString("\n"),
            style = MaterialTheme.typography.bodySmall,
        )

        Text(text = "Formats", style = MaterialTheme.typography.labelMedium)
        formats.forEach { (format, url) ->
            TextButton(
                onClick = { onClickFormat(url) },
                contentPadding = PaddingValues(4.dp),
            ) {
                Text(text = format)
            }
        }
        Text(
            text = "Download: $downloadCount",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(alignment = Alignment.End)
        )
    }
}

@Preview
@Composable
private fun PreviewGutendexContent() {
    MaterialTheme {
        Surface {
            GutendexContent(
                response = dummyResponse,
                search = "",
                getBookList = {},
                getBookListFromUrl = {},
                onSearchStringChange = {},
                lazyListState = rememberLazyListState(),
                openBookUrl = { _, _ -> },
            )
        }
    }
}


@Preview
@Composable
private fun PreviewGutendexItem() {
    MaterialTheme {
        Surface {
            GutendexItem(title = "Hoge", subjects = listOf("test", "text"), formats = mapOf(
                "Text" to "https://example.com",
                "HTML" to "https://example.com",
            ), downloadCount = 100, onClickFormat = {})
        }
    }
}

private val dummyResponse = GutendexResponse(
    count = 3,
    next = null,
    previous = null,
    results = listOf(
        GutendexResult(
            id = 1,
            title = "Hoge",
            subjects = listOf("test", "text"),
            formats = mapOf(
                "Text" to "https://example.com",
                "HTML" to "https://example.com",
            ),
            downloadCount = 100
        ),
        GutendexResult(
            id = 2,
            title = "Fuga",
            subjects = listOf("test", "text"),
            formats = mapOf(
                "Text" to "https://example.com",
                "HTML" to "https://example.com",
            ),
            downloadCount = 100
        ),
        GutendexResult(
            id = 3,
            title = "Piyo",
            subjects = listOf("test", "text"),
            formats = mapOf(
                "Text" to "https://example.com",
                "HTML" to "https://example.com",
            ),
            downloadCount = 100
        ),
    )
)

