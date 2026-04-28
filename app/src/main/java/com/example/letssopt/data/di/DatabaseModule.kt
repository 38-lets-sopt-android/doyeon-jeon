package com.example.letssopt.data.di

import android.content.Context
import androidx.room.Room
import com.example.letssopt.data.local.AppDatabase

object DatabaseModule {
    private lateinit var database: AppDatabase

    fun init (context: Context) {
        database = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    val favoriteDao by lazy { database.favoriteDao() }
}
