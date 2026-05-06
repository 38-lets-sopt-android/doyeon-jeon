package com.example.letssopt.data.local.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.letssopt.R

object AuthLocalDataSource {
    lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(
            context.getString(R.string.shared_preference_file_key),
            Context.MODE_PRIVATE
        )
    }

    fun setEmail(email: String) = prefs.edit { putString(PREFS_EMAIL_KEY, email) }

    fun setPassword(password: String) = prefs.edit { putString(PREFS_PASSWORD_KEY, password) }

    fun setLoggedInState(state: Boolean) = prefs.edit { putBoolean(PREFS_IS_LOGGED_IN_KEY, state) }

    fun getEmail(): String? = prefs.getString(PREFS_EMAIL_KEY, null)

    fun getPassword(): String? = prefs.getString(PREFS_PASSWORD_KEY, null)

    fun getIsLoggedIn(): Boolean = prefs.getBoolean(PREFS_IS_LOGGED_IN_KEY, false)

    private const val PREFS_EMAIL_KEY = "emailKey"
    private const val PREFS_PASSWORD_KEY = "passwordKey"
    private const val PREFS_IS_LOGGED_IN_KEY = "isLoggedInKey"
}
