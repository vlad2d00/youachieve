package com.example.data.storage.interfaces

interface AuthorizationStorage {
    fun getUserToken(): String
    fun saveUserToken(userToken: String)

    fun getUserId(): Long
    fun saveUserId(userId: Long)
}