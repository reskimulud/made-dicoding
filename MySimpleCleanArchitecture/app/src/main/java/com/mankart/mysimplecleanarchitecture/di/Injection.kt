package com.mankart.mysimplecleanarchitecture.di

import com.mankart.mysimplecleanarchitecture.data.IMessageDataSource
import com.mankart.mysimplecleanarchitecture.data.MessageDataSource
import com.mankart.mysimplecleanarchitecture.data.MessageRepository
import com.mankart.mysimplecleanarchitecture.domain.IMessageRepository
import com.mankart.mysimplecleanarchitecture.domain.MessageInteractor
import com.mankart.mysimplecleanarchitecture.domain.MessageUseCase

object Injection {
    fun provideUseCase(): MessageUseCase {
        val repository = provideRepository()
        return MessageInteractor(repository)
    }

    private fun provideRepository(): IMessageRepository {
        val dataSource = provideDataSource()
        return MessageRepository(dataSource)
    }

    private fun provideDataSource(): IMessageDataSource = MessageDataSource()
}