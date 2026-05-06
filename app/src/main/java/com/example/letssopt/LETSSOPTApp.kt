package com.example.letssopt

import android.app.Application
import com.example.letssopt.data.local.datasource.AuthLocalDataSource
import com.example.letssopt.data.di.DatabaseModule

class LETSSOPTApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AuthLocalDataSource.init(this)
        DatabaseModule.init(this)
    }
}
