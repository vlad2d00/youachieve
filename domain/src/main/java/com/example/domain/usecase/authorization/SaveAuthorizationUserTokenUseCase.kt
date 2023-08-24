package com.example.domain.usecase.authorization

import com.example.domain.repository.AuthorizationRepository
import javax.inject.Inject

class SaveAuthorizationUserTokenUseCase @Inject constructor(
    val authorizationRepository: AuthorizationRepository) {

    fun execute(userToken: String) {
        return authorizationRepository.saveUserToken(userToken = userToken)
    }
}