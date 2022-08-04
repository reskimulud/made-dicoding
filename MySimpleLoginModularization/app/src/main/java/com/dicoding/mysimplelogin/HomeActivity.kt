package com.dicoding.mysimplelogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.core.UserRepository
import com.dicoding.mysimplelogin.databinding.ActivityHomeBinding
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

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
                installChatModule()
            } catch (err: Exception) {
                Toast.makeText(this@HomeActivity, "Module not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun installChatModule() {
        val splitInstallManager = SplitInstallManagerFactory.create(this)
        val moduleChat = "chat"
        if (splitInstallManager.installedModules.contains(moduleChat)) {
            moveToChatActivity()
            Toast.makeText(this@HomeActivity, "Module already installed. Open it", Toast.LENGTH_SHORT).show()
        } else {
            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleChat)
                .build()

            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    Toast.makeText(this@HomeActivity, "Module installed. Open it", Toast.LENGTH_SHORT).show()
                    moveToChatActivity()
                }
                .addOnFailureListener {
                    Toast.makeText(this@HomeActivity, "Module not installed", Toast.LENGTH_SHORT).show()
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
