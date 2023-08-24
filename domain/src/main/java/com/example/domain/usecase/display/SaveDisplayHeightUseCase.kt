package com.example.domain.usecase.display

import com.example.domain.repository.DisplayRepository
import javax.inject.Inject

class SaveDisplayHeightUseCase @Inject constructor(
    val displayRepository: DisplayRepository
) {

    fun execute(height: Int) {
        return displayRepository.saveHeight(height)
    }
}