package com.example.domain.usecase.authorization

import com.example.domain.repository.AuthorizationRepository
import javax.inject.Inject

class SaveAuthorizationUserIdUseCase @Inject constructor(
    val authorizationRepository: AuthorizationRepository) {

    fun execute(userId: Long) {
        return authorizationRepository.saveUserId(userId = userId)
    }
}