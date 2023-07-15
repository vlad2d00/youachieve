package com.example.youachieve.presentation.ui.fragments.workspace.create.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Workspace
import com.example.domain.models.WorkspaceSectionType
import com.example.domain.usecase.workspace.project.CreateProjectUseCase
import com.example.domain.usecase.workspace.sections.SaveWorkspaceIdSelectedUseCase
import com.example.domain.usecase.workspace.sections.SaveWorkspaceSectionSelectedUseCase
import com.example.domain.usecase.workspace.workspace.GetWorkspaceListUseCase
import com.example.youachieve.R
import com.example.youachieve.presentation.utils.executeAsyncTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    private val createProjectUseCase: CreateProjectUseCase,
    private val getWorkspaceListUseCase: GetWorkspaceListUseCase,
    private val saveWorkspaceSectionSelectedUseCase: SaveWorkspaceSectionSelectedUseCase,
    private val saveWorkspaceIdSelectedUseCase: SaveWorkspaceIdSelectedUseCase,
): ViewModel()  {

    var workspaceSelectedItemId = 0L
    var projectName = ""
    var projectDescription = ""
    var projectIsPrivate = false

    private var workspaceListLiveMutable = MutableLiveData<List<Workspace>?>()
    val workspaceListLive: LiveData<List<Workspace>?> = workspaceListLiveMutable

    private var workspaceSelectedErrorResIdLiveMutable = MutableLiveData<Int?>()
    val workspaceSelectedErrorResIdLive: LiveData<Int?> = workspaceSelectedErrorResIdLiveMutable

    private var projectNameErrorResIdLiveMutable = MutableLiveData<Int?>()
    val projectNameErrorResIdLive: LiveData<Int?> = projectNameErrorResIdLiveMutable

    private var isCreatedLiveMutable = MutableLiveData<Boolean>(false)
    val isCreatedLive: LiveData<Boolean> = isCreatedLiveMutable


    fun loadWorkspaceList() {
        viewModelScope.executeAsyncTask(onPreExecute = {

        }, doInBackground = {
            getWorkspaceListUseCase.execute()

        }, onPostExecute = {
            workspaceListLiveMutable.value = it
        })
    }

    private fun getSelectedWorkspaceId(): Long? {
        if (workspaceListLive.value == null) {
            return null
        }
        if (workspaceListLive.value?.isEmpty() == true) {
            return null
        }
        return workspaceListLive.value!![workspaceSelectedItemId.toInt()].id
    }

    fun createProject() {
        if (projectName == "") {
            projectNameErrorResIdLiveMutable.value = R.string.error_workspace_no_name
            return
        }
        val workspaceId = getSelectedWorkspaceId()
        if (workspaceId == null) {
            workspaceSelectedErrorResIdLiveMutable.value = R.string.error_project_no_workspace
            return
        }

        viewModelScope.executeAsyncTask(onPreExecute = {

        }, doInBackground = {
            createProjectUseCase.execute(
                workspaceId = workspaceId,
                name = projectName,
                description = projectDescription,
                isPrivate = projectIsPrivate
            )

            saveWorkspaceSectionSelectedUseCase.execute(WorkspaceSectionType.PROJECTS)
            saveWorkspaceIdSelectedUseCase.execute(workspaceId)

        }, onPostExecute = {
            isCreatedLiveMutable.value = true
        })
    }

}