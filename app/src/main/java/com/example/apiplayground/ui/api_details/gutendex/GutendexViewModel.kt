package com.example.apiplayground.ui.api_details.gutendex

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiplayground.model.gutendex.GutendexRepository
import com.example.apiplayground.model.gutendex.GutendexResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GutendexViewModel @Inject constructor(
    private val repository: GutendexRepository
) : ViewModel() {
    val bookResponse: MutableLiveData<GutendexResponse?> by lazy {
        MutableLiveData<GutendexResponse?>(
            null
        )
    }

    val searchString: MutableLiveData<String> by lazy {
        MutableLiveData<String>(
            ""
        )
    }

    fun onSearchStringChange(newSearchString: String) {
        searchString.postValue(newSearchString)
    }

    fun getBookList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getBookList(searchString.value)
            bookResponse.postValue(response)
        }
    }

    fun getBookListFromUrl(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getBookListFromUrl(url)
            bookResponse.postValue(response)
        }
    }

    fun openBookUrl(url: String, context: Context) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }

}