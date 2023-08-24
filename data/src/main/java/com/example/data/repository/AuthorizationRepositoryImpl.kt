package com.example.data.repository

import com.example.data.storage.interfaces.AuthorizationStorage
import com.example.domain.repository.AuthorizationRepository

class AuthorizationRepositoryImpl(
    private val authorizationStorage: AuthorizationStorage
): AuthorizationRepository {

    override fun getUserToken(): String {
        return authorizationStorage.getUserToken()
    }

    override fun saveUserToken(userToken: String) {
        authorizationStorage.saveUserToken(userToken)
    }

    override fun getUserId(): Long {
        return authorizationStorage.getUserId()
    }

    override fun saveUserId(userId: Long) {
        authorizationStorage.saveUserId(userId)
    }

}