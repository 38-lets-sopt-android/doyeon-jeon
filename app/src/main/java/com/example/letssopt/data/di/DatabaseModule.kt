package com.example.letssopt.data.di

import android.content.Context
import com.example.letssopt.data.local.AppDatabase

object DatabaseModule {
    private lateinit var database: AppDatabase

    fun init(context: Context) {
        database = AppDatabase.getDatabase(context)
    }

    val favoriteDao by lazy { database.favoriteDao() }
}
