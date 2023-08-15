package com.example.apiplayground.ui.api_details.random_dogs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiplayground.model.random_dogs.RandomDogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomDogsViewModel @Inject constructor(
    private val repository: RandomDogsRepository
) : ViewModel() {
    val dogMediaUrl: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>(null)
    }


    fun getDogMediaUrl() {
        viewModelScope.launch(Dispatchers.IO) {
            val url = repository.getRandomDogsUrl(listOf("jpg", "png", "jpeg", "gif"))
            dogMediaUrl.postValue(url)
        }
    }
}