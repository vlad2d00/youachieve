package com.example.domain.repository

interface AuthorizationRepository {
    fun getUserToken(): String
    fun saveUserToken(userToken: String)

    fun getUserId(): Long
    fun saveUserId(userId: Long)
}