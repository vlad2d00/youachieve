package com.example.domain.usecase.main

import com.example.domain.models.base.MainSectionType
import com.example.domain.repository.MainSectionRepository
import javax.inject.Inject

class SaveMainSectionSelectedUseCase @Inject constructor(
    val mainSectionRepository: MainSectionRepository) {

    fun execute(mainSectionType: MainSectionType) {
        return mainSectionRepository.saveSelected(mainSectionType)
    }
}