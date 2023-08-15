package com.example.apiplayground.di

import com.example.apiplayground.model.emoji_hub.EmojiHubRepository
import com.example.apiplayground.model.emoji_hub.EmojiHubRepositoryImpl
import com.example.apiplayground.model.random_dogs.RandomDogsRepository
import com.example.apiplayground.model.random_dogs.RandomDogsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RandomDogsRepositoryModules {
    @Binds
    abstract fun bindRandomDogsRepository(
        randomDogsRepositoryImpl: RandomDogsRepositoryImpl
    ): RandomDogsRepository
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class EmojiHubRepositoryModules {
    @Binds
    abstract fun bindEmojiHubRepository(
        emojiHubRepositoryImpl: EmojiHubRepositoryImpl
    ): EmojiHubRepository
}

