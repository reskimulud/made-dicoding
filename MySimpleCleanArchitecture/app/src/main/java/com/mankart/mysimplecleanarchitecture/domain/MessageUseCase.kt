package com.mankart.mysimplecleanarchitecture.domain

interface MessageUseCase {
    fun getMessage(name: String) : MessageEntity
}