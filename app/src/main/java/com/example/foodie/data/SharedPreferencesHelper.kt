package com.example.foodie.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    companion object {
        private const val PREFS_NAME = "MyPrefs"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
        private const val KEY_REMEMBER_USER = "rememberUser"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var rememberUser: Boolean
        get() = sharedPreferences.getBoolean(KEY_REMEMBER_USER, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_REMEMBER_USER, value).apply()

    var username: String?
        get() = sharedPreferences.getString(KEY_USERNAME, null)
        set(value) = sharedPreferences.edit().putString(KEY_USERNAME, value).apply()

    var password: String?
        get() = sharedPreferences.getString(KEY_PASSWORD, null)
        set(value) = sharedPreferences.edit().putString(KEY_PASSWORD, value).apply()

    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }

    fun isLoggedIn(): Boolean {
        val username = sharedPreferences.getString(KEY_USERNAME, null)
        val password = sharedPreferences.getString(KEY_PASSWORD, null)
        return !username.isNullOrEmpty() && !password.isNullOrEmpty()
    }
}