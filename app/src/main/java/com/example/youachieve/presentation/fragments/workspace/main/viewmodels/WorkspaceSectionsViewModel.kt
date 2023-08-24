package com.example.youachieve.presentation.fragments.workspace.main.viewmodels

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.workspace.WorkspaceSection
import com.example.domain.models.workspace.WorkspaceSectionType
import com.example.domain.usecase.workspace.sections.*
import com.example.youachieve.R
import com.example.youachieve.presentation.fragments.workspace.sections.*
import com.example.youachieve.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkspaceSectionsViewModel @Inject constructor(
    private val getWorkspaceSectionListUseCase: GetWorkspaceSectionListUseCase,
    private val getWorkspaceSectionSelectedUseCase: GetWorkspaceSectionSelectedUseCase,
    private val saveWorkspaceSectionSelectedUseCase: SaveWorkspaceSectionSelectedUseCase,
    private val getWorkspaceSectionCategoryUseCase: GetWorkspaceSectionCategoryUseCase,
    private val getWorkspaceSectionDistanceUseCase: GetWorkspaceSectionDistanceUseCase
): BaseViewModel() {

    private var sectionsListLiveMutable = MutableLiveData(
        getWorkspaceSectionListUseCase.execute(
            workspaceSectionCategoryType = getWorkspaceSectionCategoryUseCase.execute()
        ))
    val sectionListLive: LiveData<List<WorkspaceSection>> = sectionsListLiveMutable


    fun getSectionSelected(): WorkspaceSectionType {
        return getWorkspaceSectionSelectedUseCase.execute()
    }

    fun loadSection(sectionTargetType: WorkspaceSectionType,
                    isAnimate: Boolean,
                    fragmentManager: FragmentManager
    ) {
        if (sectionsListLiveMutable.value != null) {
            val sections = sectionsListLiveMutable.value!!
            for (section in sections) {
                section.isSelected = section.type == sectionTargetType
            }
            sectionsListLiveMutable.value = sections
        }

        var enterResId: Int = 0
        var exitResId: Int = 0

        if (isAnimate) {
            val distance = getWorkspaceSectionDistanceUseCase.execute(
                sectionTypeFrom = getWorkspaceSectionSelectedUseCase.execute(),
                sectionTypeTo = sectionTargetType,
                category = getWorkspaceSectionCategoryUseCase.execute()
            )

            if (distance <= -3) {
                enterResId = R.anim.slide_from_left_x3
                exitResId = R.anim.slide_to_right_x3
            }
            else if (distance == -2) {
                enterResId = R.anim.slide_from_left_x2
                exitResId = R.anim.slide_to_right_x2
            }
            else if (distance == -1) {
                enterResId = R.anim.slide_from_left
                exitResId = R.anim.slide_to_right
            }
            else if (distance == 0) {
                enterResId = R.anim.appearance
                exitResId = R.anim.disappearance
            }
            else if (distance == 1) {
                enterResId = R.anim.slide_from_right
                exitResId = R.anim.slide_to_left
            }
            else if (distance == 2) {
                enterResId = R.anim.slide_from_right_x2
                exitResId = R.anim.slide_to_left_x2
            }
            else if (distance >= 3) {
                enterResId = R.anim.slide_from_right_x3
                exitResId = R.anim.slide_to_left_x3
            }
        }

        val fragment = toFragment(sectionTargetType)
        fragmentManager.commit {
            setCustomAnimations(
                enterResId,
                exitResId
            )
            replace(R.id.workspaceSectionsDetailFragment, fragment)
            addToBackStack(null)
        }
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.workspaceSectionsDetailFragment, fragment).commit()

        saveWorkspaceSectionSelectedUseCase.execute(sectionTargetType)
    }

    private fun toFragment(workspaceSectionType: WorkspaceSectionType): Fragment {
        return when (workspaceSectionType) {
            WorkspaceSectionType.PROJECTS -> WorkspaceProjectsFragment()
            WorkspaceSectionType.TASKS -> WorkspaceTasksFragment()
            WorkspaceSectionType.USERS -> WorkspaceUsersFragment()
            WorkspaceSectionType.ACTIONS -> WorkspaceActionsFragment()
        }
    }

}