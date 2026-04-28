package com.example.letssopt

import android.app.Application
import com.example.letssopt.data.auth.AuthRepository

class LETSSOPTApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AuthRepository.init(this)
    }
}
