package com.example.data.storage.db.sharedprefs

import android.content.Context
import com.example.data.storage.interfaces.AuthorizationStorage

class AuthorizationSharedPrefs(context: Context) : AuthorizationStorage {

    companion object {
        private const val SHARED_PREFS_NAME = "authentication"
        private const val USER_TOKEN_KEY = "token"
        private const val USER_TOKEN_VALUE_DEFAULT = ""
        private const val USER_ID_KEY = "userId"
        private const val USER_ID_VALUE_DEFAULT = 0L
    }

    private val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)


    override fun getUserToken(): String {
        return sharedPrefs.getString(USER_TOKEN_KEY, USER_TOKEN_VALUE_DEFAULT).toString()
    }

    override fun saveUserToken(userToken: String) {
        sharedPrefs.edit().putString(USER_TOKEN_KEY, userToken).apply()
    }

    override fun getUserId(): Long {
        return sharedPrefs.getLong(USER_ID_KEY, USER_ID_VALUE_DEFAULT)
    }

    override fun saveUserId(userId: Long) {
        sharedPrefs.edit().putLong(USER_ID_KEY, userId).apply()
    }

}