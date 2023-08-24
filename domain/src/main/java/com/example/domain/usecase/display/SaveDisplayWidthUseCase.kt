package com.example.domain.usecase.display

import com.example.domain.repository.DisplayRepository
import javax.inject.Inject

class SaveDisplayWidthUseCase @Inject constructor(
    val displayRepository: DisplayRepository
) {

    fun execute(width: Int) {
        return displayRepository.saveWidth(width)
    }
}