package com.mankart.mysimplecleanarchitecture.data

import com.mankart.mysimplecleanarchitecture.domain.MessageEntity

interface IMessageDataSource {
    fun getMessageFromSource(name: String) : MessageEntity
}