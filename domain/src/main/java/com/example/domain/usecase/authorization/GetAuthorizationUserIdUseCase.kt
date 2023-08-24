package com.example.domain.usecase.authorization

import com.example.domain.repository.AuthorizationRepository
import javax.inject.Inject

class GetAuthorizationUserIdUseCase @Inject constructor(
    val authorizationRepository: AuthorizationRepository) {

    fun execute(): Long {
        return authorizationRepository.getUserId()
    }
}