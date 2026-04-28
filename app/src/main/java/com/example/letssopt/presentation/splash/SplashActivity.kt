package com.example.letssopt.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.letssopt.data.local.AuthRepository
import com.example.letssopt.presentation.auth.login.LoginActivity
import com.example.letssopt.presentation.main.MainActivity

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val destination = if (AuthRepository.isLoggedIn()) {
            MainActivity::class.java
        } else {
            LoginActivity::class.java
        }

        startActivity(Intent(this, destination))
        finish()
    }
}
