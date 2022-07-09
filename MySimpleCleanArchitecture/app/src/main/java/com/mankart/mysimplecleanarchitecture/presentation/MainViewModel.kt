package com.mankart.mysimplecleanarchitecture.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mankart.mysimplecleanarchitecture.domain.MessageEntity
import com.mankart.mysimplecleanarchitecture.domain.MessageUseCase

class MainViewModel(private val useCase: MessageUseCase) : ViewModel() {
    private val _message = MutableLiveData<MessageEntity>()
    val message: LiveData<MessageEntity> get() = _message

    fun setName(name: String) {
        _message.value = useCase.getMessage(name)
    }
}