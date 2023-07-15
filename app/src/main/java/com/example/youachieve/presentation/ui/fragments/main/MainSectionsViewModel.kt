package com.example.youachieve.presentation.ui.fragments.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.domain.models.MainSectionType
import com.example.domain.usecase.main.GetMainSectionSelectedUseCase
import com.example.domain.usecase.main.SaveMainSectionSelectedUseCase
import com.example.youachieve.R
import com.example.youachieve.presentation.ui.fragments.home.HomeFragment
import com.example.youachieve.presentation.ui.fragments.messenger.MessengerFragment
import com.example.youachieve.presentation.ui.fragments.services.ServicesFragment
import com.example.youachieve.presentation.ui.fragments.settings.SettingsFragment
import com.example.youachieve.presentation.ui.fragments.workspace.main.WorkspaceFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainSectionsViewModel @Inject constructor(
    private val getMainSectionSelectedUseCase: GetMainSectionSelectedUseCase,
    private val saveMainSectionSelectedUseCase: SaveMainSectionSelectedUseCase,
) : ViewModel() {

    private val sections = mapOf(
        R.id.menuMainNavigationHome to MainSectionType.HOME,
        R.id.menuMainNavigationMessenger to MainSectionType.MESSENGER,
        R.id.menuMainNavigationWorkspace to MainSectionType.WORKSPACE,
        R.id.menuMainNavigationServices to MainSectionType.SERVICES,
        R.id.menuMainNavigationSettings to MainSectionType.SETTINGS,
    )

    fun getSectionSelected(): MainSectionType {
        return getMainSectionSelectedUseCase.execute()
    }

    fun loadSection(mainSectionType: MainSectionType,
                    fragmentManager: FragmentManager
    ) {

        val fragment = toFragment(mainSectionType)
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.mainSectionsFragment, fragment).commit()

        saveMainSectionSelectedUseCase.execute(mainSectionType)
    }

    private fun toFragment(mainSectionType: MainSectionType): Fragment {
        return when (mainSectionType) {
            MainSectionType.HOME -> HomeFragment()
            MainSectionType.MESSENGER -> MessengerFragment()
            MainSectionType.WORKSPACE -> WorkspaceFragment()
            MainSectionType.SERVICES -> ServicesFragment()
            MainSectionType.SETTINGS -> SettingsFragment()
        }
    }

    fun getSectionItemById(id: Int): MainSectionType {
        return sections.getValue(id)
    }

    fun getSectionIdByItem(mainSectionType: MainSectionType): Int {
        val sectionsReverse = sections.entries.associateBy({ it.value }, { it.key })
        return sectionsReverse.getValue(mainSectionType)
    }

}