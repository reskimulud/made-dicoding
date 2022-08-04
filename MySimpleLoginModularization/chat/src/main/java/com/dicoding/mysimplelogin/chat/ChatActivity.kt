package com.dicoding.mysimplelogin.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.core.SessionManager
import com.dicoding.core.UserRepository
import com.dicoding.mysimplelogin.chat.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val session = SessionManager(this)
        val userRepository = UserRepository.getInstance(session)
        binding.tvChatFeature.text = "Hello ${userRepository.getUser()}! \n Welcome to Chat Feature"
    }
}