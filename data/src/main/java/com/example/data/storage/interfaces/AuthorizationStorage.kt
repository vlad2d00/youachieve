package com.example.data.storage.interfaces

interface AuthorizationStorage {
    fun getToken(): String
    fun saveToken(token: String)

    fun getUserId(): Long
    fun saveUserId(userId: Long)
}