package com.example.apiplayground.ui.api_details.emoji_hub

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiplayground.model.emoji_hub.EmojiHubRepository
import com.example.apiplayground.model.emoji_hub.EmojiHubResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmojiHubViewModel @Inject constructor(private val repository: EmojiHubRepository) :
    ViewModel() {

    private val emojiListStateFlow = MutableStateFlow<EmojiHubResponse?>(null)
    val emojiList: Flow<EmojiHubResponse?> = emojiListStateFlow

    private val waitingStateFlow = MutableStateFlow(false)
    val waiting: Flow<Boolean> = waitingStateFlow.asStateFlow()

    fun getEmojiList() {
        waitingStateFlow.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getRandomEmoji()
            emojiListStateFlow.value = response
            waitingStateFlow.value = false
        }
    }

    fun convertUnicodeToEmoji(unicodeString: String): String {
        val parts = unicodeString.split("+")
        val codePoint = parts[1].toInt(16)
        return String(Character.toChars(codePoint))
    }


}
