package com.example.youachieve.presentation.fragments.workspace.main.viewmodels

import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.workspace.Workspace
import com.example.domain.models.workspace.WorkspaceSectionCategory
import com.example.domain.models.workspace.WorkspaceSectionType
import com.example.domain.usecase.display.GetDisplayWidthUseCase
import com.example.domain.usecase.workspace.project.GetProjectUseCase
import com.example.domain.usecase.workspace.workspace.GetWorkspaceUseCase
import com.example.domain.usecase.workspace.sections.*
import com.example.domain.usecase.workspace.workspace.GetWorkspaceListUseCase
import com.example.domain.utils.LogData
import com.example.youachieve.R
import com.example.youachieve.presentation.fragments.workspace.main.WorkspaceSectionsFragment
import com.example.youachieve.presentation.base.BaseViewModel
import com.example.youachieve.presentation.utils.executeAsyncTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkspaceViewModel @Inject constructor(
    private val getWorkspaceSectionCategoryUseCase: GetWorkspaceSectionCategoryUseCase,
    private val saveWorkspaceSectionSelectedUseCase: SaveWorkspaceSectionSelectedUseCase,

    private val getWorkspaceIdSelectedUseCase: GetWorkspaceIdSelectedUseCase,
    private val saveWorkspaceIdSelectedUseCase: SaveWorkspaceIdSelectedUseCase,
    private val getProjectIdSelectedUseCase: GetProjectIdSelectedUseCase,
    private val saveProjectIdSelectedUseCase: SaveProjectIdSelectedUseCase,

    private val getWorkspaceUseCase: GetWorkspaceUseCase,
    private val getProjectUseCase: GetProjectUseCase,
    private val getWorkspaceListUseCase: GetWorkspaceListUseCase,

    private val getDisplayWidthUseCase: GetDisplayWidthUseCase,
): BaseViewModel() {

    private var workspaceSectionLiveMutable = MutableLiveData<WorkspaceSectionCategory?>()
    val workspaceSectionLive: LiveData<WorkspaceSectionCategory?> = workspaceSectionLiveMutable

    private var headerTextLiveMutable = MutableLiveData<String?>()
    val headerTextLive: LiveData<String?> = headerTextLiveMutable

    private var workspaceListLiveMutable = MutableLiveData<List<Workspace>?>()
    val workspaceListLive: LiveData<List<Workspace>?> = workspaceListLiveMutable


    fun loadSection(fragmentManager: FragmentManager, isAnimated: Boolean = false) {
        Log.d(LogData.TAG_APP, "${this::class.simpleName} loadSection")
        val fragment = WorkspaceSectionsFragment()

        if (isAnimated) {
            fragmentManager.commit {
                setCustomAnimations(
                    R.anim.appearance,
                    R.anim.disappearance
                )
                replace(R.id.workspaceSectionsFragment, fragment)
            }
        }
        else {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.workspaceSectionsFragment, fragment)
            transaction.commit()
        }
    }

    fun loadWorkspaceList() {
        viewModelScope.executeAsyncTask(
            onPreExecute = {

            }, doInBackground = {
                getWorkspaceListUseCase.execute()

            }, onPostExecute = {
                workspaceListLiveMutable.value = it
            })
    }

    fun updateHeader() {
        viewModelScope.executeAsyncTask(
            onPreExecute = {

        }, doInBackground = {
            val category = getWorkspaceSectionCategoryUseCase.execute()
            val text = when(category) {
                WorkspaceSectionCategory.WORKSPACES_ALL -> null

                WorkspaceSectionCategory.WORKSPACE -> {
                    val workspaceId = getWorkspaceIdSelectedUseCase.execute()
                    val workspace = getWorkspaceUseCase.execute(workspaceId)
                    workspace?.name
                }
                WorkspaceSectionCategory.PROJECT -> {
                    val projectId = getProjectIdSelectedUseCase.execute()
                    val project = getProjectUseCase.execute(projectId)
                    project?.name
                }
            }
            Pair(category, text)

        }, onPostExecute = {
            workspaceSectionLiveMutable.value = it.first
            headerTextLiveMutable.value = it.second
        })
    }

    fun switchToHigherLevel(fragmentManager: FragmentManager): Boolean {
        val category = getWorkspaceSectionCategoryUseCase.execute()
        var isUp = false

        if (category == WorkspaceSectionCategory.PROJECT) {
            saveProjectIdSelectedUseCase.execute(0L)
            saveWorkspaceSectionSelectedUseCase.execute(WorkspaceSectionType.PROJECTS)
            isUp = true
        }
        else if (category == WorkspaceSectionCategory.WORKSPACE) {
            saveWorkspaceIdSelectedUseCase.execute(0L)
            saveWorkspaceSectionSelectedUseCase.execute(WorkspaceSectionType.PROJECTS)
            isUp = true
        }

        if (isUp) {
            updateHeader()
            loadSection(fragmentManager = fragmentManager, isAnimated = true)
        }
        return isUp
    }

    fun switchToWorkspace(workspaceId: Long, fragmentManager: FragmentManager) {
        if (getWorkspaceIdSelectedUseCase.execute() == workspaceId) {
            return
        }

        saveWorkspaceIdSelectedUseCase.execute(workspaceId)
        saveProjectIdSelectedUseCase.execute(0L)

        updateHeader()
        loadSection(fragmentManager = fragmentManager, isAnimated = true)
    }

    fun switchToProject(workspaceId: Long, projectId: Long, fragmentManager: FragmentManager) {
        val projectIdSelected = getProjectIdSelectedUseCase.execute()
        if (projectIdSelected == projectId) {
            return
        }

        saveWorkspaceIdSelectedUseCase.execute(workspaceId)
        saveProjectIdSelectedUseCase.execute(projectId)

        updateHeader()
        loadSection(fragmentManager = fragmentManager, isAnimated = true)
    }

    fun getDisplayWidth(): Int {
        return getDisplayWidthUseCase.execute()
    }

}