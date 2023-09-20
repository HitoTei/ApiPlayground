package com.example.apiplayground.ui.api_details.gutendex

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

    fun getBookList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getBookList()
            bookResponse.postValue(response)
        }
    }

}