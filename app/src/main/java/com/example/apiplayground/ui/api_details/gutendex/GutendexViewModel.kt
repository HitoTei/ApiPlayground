package com.example.apiplayground.ui.api_details.gutendex

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiplayground.model.gutendex.GutendexRepository
import com.example.apiplayground.model.gutendex.GutendexResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GutendexViewModel @Inject constructor(
    private val repository: GutendexRepository
) : ViewModel() {

    private val bookResponseStateFlow = MutableStateFlow<GutendexResponse?>(null)
    val bookResponse: Flow<GutendexResponse?> = bookResponseStateFlow

    private val searchStringStateFlow = MutableStateFlow("")
    val searchString: Flow<String> = searchStringStateFlow

    private val waitingStateFlow = MutableStateFlow(false)
    val waiting: Flow<Boolean> = waitingStateFlow.asStateFlow()

    fun onSearchStringChange(newSearchString: String) {
        searchStringStateFlow.value = newSearchString
    }

    fun getBookList(search: String = "") {
        waitingStateFlow.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getBookList(search)
            bookResponseStateFlow.value = response
            waitingStateFlow.value = false
        }
    }

    fun getBookListFromUrl(url: String) {
        waitingStateFlow.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getBookListFromUrl(url)
            bookResponseStateFlow.value = response
            waitingStateFlow.value = false
        }
    }

    fun openBookUrl(url: String, context: Context) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }

}
