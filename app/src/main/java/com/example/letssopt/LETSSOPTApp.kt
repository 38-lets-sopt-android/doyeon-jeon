package com.example.letssopt

import android.app.Application
import com.example.letssopt.data.auth.AuthPreferences
import com.example.letssopt.data.di.DatabaseModule

class LETSSOPTApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AuthPreferences.init(this)
        DatabaseModule.init(this)
    }
}
