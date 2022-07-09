package com.mankart.mysimplecleanarchitecture.domain

interface IMessageRepository {
    fun getWelcomeMessage(name: String) : MessageEntity
}