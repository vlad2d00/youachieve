package com.example.domain.usecase.display

import com.example.domain.repository.DisplayRepository
import javax.inject.Inject

class GetDisplayWidthUseCase @Inject constructor(
    val displayRepository: DisplayRepository
) {

    fun execute(): Int {
        return displayRepository.getWidth()
    }
}