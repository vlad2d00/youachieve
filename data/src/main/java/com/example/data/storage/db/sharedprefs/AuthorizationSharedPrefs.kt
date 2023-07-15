package com.example.data.storage.db.sharedprefs

import android.content.Context
import com.example.data.storage.interfaces.AuthorizationStorage

private const val SHARED_PREFS_NAME = "authentication"
private const val TOKEN_KEY = "token"
private const val TOKEN_VALUE_DEFAULT = ""
private const val USER_ID_KEY = "userId"
private const val USER_ID_VALUE_DEFAULT = 0L

class AuthorizationSharedPrefs(context: Context) : AuthorizationStorage {

    private val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)


    override fun getToken(): String {
        return sharedPrefs.getString(TOKEN_KEY, TOKEN_VALUE_DEFAULT).toString()
    }

    override fun saveToken(token: String) {
        sharedPrefs.edit().putString(TOKEN_KEY, token).apply()
    }

    override fun getUserId(): Long {
        return sharedPrefs.getLong(USER_ID_KEY, USER_ID_VALUE_DEFAULT).toLong()
    }

    override fun saveUserId(userId: Long) {
        sharedPrefs.edit().putLong(USER_ID_KEY, userId).apply()
    }

}