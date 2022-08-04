package com.dicoding.mysimplelogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.core.UserRepository
import com.dicoding.mysimplelogin.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sesi = com.dicoding.core.SessionManager(this)
        userRepository = UserRepository.getInstance(sesi)

        binding.tvWelcome.text = "Welcome ${userRepository.getUser()}"

        binding.btnLogout.setOnClickListener {
            userRepository.logoutUser()
            moveToMainActivity()
        }

        binding.fabChat.setOnClickListener {
            try {
                moveToChatActivity()
            } catch (err: Exception) {
                Toast.makeText(this@HomeActivity, "Module not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun moveToChatActivity() {
        val intent = Intent(this, Class.forName("com.dicoding.mysimplelogin.chat.ChatActivity"))
        startActivity(intent)
    }

    private fun moveToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
