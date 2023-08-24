package com.example.domain.usecase.main

import com.example.domain.models.base.MainSectionType
import com.example.domain.repository.MainSectionRepository
import javax.inject.Inject

class GetMainSectionSelectedUseCase @Inject constructor(
    val mainSectionRepository: MainSectionRepository) {

    fun execute(): MainSectionType {
        return mainSectionRepository.getSelected()
    }
}