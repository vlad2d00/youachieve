package com.example.domain.usecase.authorization

import com.example.domain.repository.AuthorizationRepository
import javax.inject.Inject

class GetAuthorizationUserTokenUseCase @Inject constructor(
    val authorizationRepository: AuthorizationRepository) {

    fun execute(): String {
        return authorizationRepository.getUserToken()
    }
}