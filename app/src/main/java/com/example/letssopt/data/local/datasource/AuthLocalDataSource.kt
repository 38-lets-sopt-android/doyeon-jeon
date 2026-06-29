package com.example.letssopt.data.local.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.letssopt.R

object AuthLocalDataSource {
    lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(
            context.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE
        )
    }

    fun setUserId(userId: Long) = prefs.edit { putLong(USER_ID_KEY, userId) }

    fun getUserId(): Long? =
        if (prefs.contains(USER_ID_KEY)) {
            prefs.getLong(USER_ID_KEY, 0L)
        } else {
            null
        }

    private const val USER_ID_KEY = "userIdKey"
}
