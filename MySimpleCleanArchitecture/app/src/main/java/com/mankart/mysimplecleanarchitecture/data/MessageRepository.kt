package com.mankart.mysimplecleanarchitecture.data

import com.mankart.mysimplecleanarchitecture.domain.IMessageRepository

class MessageRepository(private val dataSource: IMessageDataSource) : IMessageRepository {
    override fun getWelcomeMessage(name: String) = dataSource.getMessageFromSource(name)
}