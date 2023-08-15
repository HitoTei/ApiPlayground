package com.example.apiplayground.ui.api_details.emoji_hub

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiplayground.model.emoji_hub.EmojiHubRepository
import com.example.apiplayground.model.emoji_hub.EmojiHubResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmojiHubViewModel @Inject constructor(private val repository: EmojiHubRepository) :
    ViewModel() {
    val emojiList: MutableLiveData<EmojiHubResponse?> by lazy {
        MutableLiveData<EmojiHubResponse?>(null)
    }

    fun getEmojiList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getRandomEmoji()
            emojiList.postValue(response)
        }
    }

    fun convertUnicodeToEmoji(unicodeString: String): String {
        val parts = unicodeString.split("+")
        val codePoint = parts[1].toInt(16)
        return String(Character.toChars(codePoint))
    }


}
