package com.mankart.mysimplecleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mankart.mysimplecleanarchitecture.di.Injection
import com.mankart.mysimplecleanarchitecture.domain.MessageUseCase

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val useCase: MessageUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(useCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: MainViewModelFactory? = null

        fun getInstance(): MainViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MainViewModelFactory(Injection.provideUseCase())
            }
    }
}