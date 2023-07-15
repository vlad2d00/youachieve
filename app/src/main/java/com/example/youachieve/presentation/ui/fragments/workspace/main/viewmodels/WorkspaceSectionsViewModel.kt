package com.example.youachieve.presentation.ui.fragments.workspace.main.viewmodels

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.WorkspaceSection
import com.example.domain.models.WorkspaceSectionType
import com.example.domain.usecase.workspace.sections.GetWorkspaceSectionCategoryUseCase
import com.example.domain.usecase.workspace.sections.GetWorkspaceSectionListUseCase
import com.example.domain.usecase.workspace.sections.GetWorkspaceSectionSelectedUseCase
import com.example.domain.usecase.workspace.sections.SaveWorkspaceSectionSelectedUseCase
import com.example.youachieve.R
import com.example.youachieve.presentation.ui.fragments.workspace.sections.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class WorkspaceSectionsViewModel @Inject constructor(
    private val getWorkspaceSectionListUseCase: GetWorkspaceSectionListUseCase,
    private val getWorkspaceSectionSelectedUseCase: GetWorkspaceSectionSelectedUseCase,
    private val saveWorkspaceSectionSelectedUseCase: SaveWorkspaceSectionSelectedUseCase,
    private val getWorkspaceSectionCategoryUseCase: GetWorkspaceSectionCategoryUseCase,
): ViewModel() {

    private var sectionsListLiveMutable = MutableLiveData(
        getWorkspaceSectionListUseCase.execute(
            workspaceSectionCategoryType = getWorkspaceSectionCategoryUseCase.execute()
        ))
    val sectionListLive: LiveData<List<WorkspaceSection>> = sectionsListLiveMutable


    fun getSectionSelected(): WorkspaceSectionType {
        return getWorkspaceSectionSelectedUseCase.execute()
    }

    fun loadSection(workspaceSectionType: WorkspaceSectionType,
                    isAnimate: Boolean,
                    fragmentManager: FragmentManager
    ) {
        if (sectionsListLiveMutable.value != null) {
            val sections = sectionsListLiveMutable.value!!
            for (section in sections) {
                section.isSelected = section.type == workspaceSectionType
            }
            sectionsListLiveMutable.value = sections
        }

        var enterResId: Int = 0
        var exitResId: Int = 0

        if (isAnimate) {
            val target = WorkspaceSectionType.toInt(workspaceSectionType)
            val selected = WorkspaceSectionType.toInt(getWorkspaceSectionSelectedUseCase.execute())

            if (target != selected) {
                if (target > selected) {
                    enterResId = when {
                        abs(target - selected) == 1 -> R.anim.slide_from_right
                        abs(target - selected) == 2 -> R.anim.slide_from_right_x2
                        else -> R.anim.slide_from_right_x3
                    }
                    exitResId = when {
                        abs(target - selected) == 1 -> R.anim.slide_to_left
                        abs(target - selected) == 2 -> R.anim.slide_to_left_x2
                        else -> R.anim.slide_to_left_x3
                    }
                }
                else {
                    enterResId = when {
                        abs(target - selected) == 1 -> R.anim.slide_from_left
                        abs(target - selected) == 2 -> R.anim.slide_from_left_x2
                        else -> R.anim.slide_from_left_x3
                    }
                    exitResId = when {
                        abs(target - selected) == 1 -> R.anim.slide_to_right
                        abs(target - selected) == 2 -> R.anim.slide_to_right_x2
                        else -> R.anim.slide_to_right_x3
                    }
                }
            }
        }

        val fragment = toFragment(workspaceSectionType)
        fragmentManager.commit {
            setCustomAnimations(
                enterResId,
                exitResId
            )
            replace(R.id.workspaceSectionsDetailFragment, fragment)
        }
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.workspaceSectionsDetailFragment, fragment).commit()

        saveWorkspaceSectionSelectedUseCase.execute(workspaceSectionType)
    }

    private fun toFragment(workspaceSectionType: WorkspaceSectionType): Fragment {
        return when (workspaceSectionType) {
            WorkspaceSectionType.WORKSPACES -> WorkspaceWorkspacesFragment()
            WorkspaceSectionType.PROJECTS -> WorkspaceProjectsFragment()
            WorkspaceSectionType.TASKS -> WorkspaceTasksFragment()
            WorkspaceSectionType.USERS -> WorkspaceUsersFragment()
            WorkspaceSectionType.ACTIONS -> WorkspaceActionsFragment()
        }
    }

}