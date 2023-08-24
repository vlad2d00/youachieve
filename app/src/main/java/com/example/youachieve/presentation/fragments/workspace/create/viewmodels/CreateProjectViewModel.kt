package com.example.youachieve.presentation.fragments.workspace.create.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Workspace
import com.example.domain.models.workspace.WorkspaceSectionType
import com.example.domain.usecase.workspace.project.CreateProjectUseCase
import com.example.domain.usecase.workspace.sections.GetWorkspaceIdSelectedUseCase
import com.example.domain.usecase.workspace.sections.SaveProjectIdSelectedUseCase
import com.example.domain.usecase.workspace.sections.SaveWorkspaceIdSelectedUseCase
import com.example.domain.usecase.workspace.sections.SaveWorkspaceSectionSelectedUseCase
import com.example.domain.usecase.workspace.workspace.GetWorkspaceListUseCase
import com.example.youachieve.R
import com.example.youachieve.presentation.base.BaseViewModel
import com.example.youachieve.presentation.utils.executeAsyncTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    private val createProjectUseCase: CreateProjectUseCase,
    private val getWorkspaceListUseCase: GetWorkspaceListUseCase,
    private val saveWorkspaceSectionSelectedUseCase: SaveWorkspaceSectionSelectedUseCase,
    private val saveWorkspaceIdSelectedUseCase: SaveWorkspaceIdSelectedUseCase,
    private val saveProjectIdSelectedUseCase: SaveProjectIdSelectedUseCase,
    private val getWorkspaceIdSelectedUseCase: GetWorkspaceIdSelectedUseCase,
): BaseViewModel()  {

    private var workspaceListLiveMutable = MutableLiveData<List<Workspace>?>()
    val workspaceListLive: LiveData<List<Workspace>?> = workspaceListLiveMutable

    private var workspaceSelectedErrorResIdLiveMutable = MutableLiveData<Int?>()
    val workspaceSelectedErrorResIdLive: LiveData<Int?> = workspaceSelectedErrorResIdLiveMutable

    private var projectNameErrorResIdLiveMutable = MutableLiveData<Int?>()
    val projectNameErrorResIdLive: LiveData<Int?> = projectNameErrorResIdLiveMutable

    private var isCreatedLiveMutable = MutableLiveData<Boolean>(false)
    val isCreatedLive: LiveData<Boolean> = isCreatedLiveMutable


    fun loadWorkspaceList() {
        viewModelScope.executeAsyncTask(
            onPreExecute = {

        }, doInBackground = {
            getWorkspaceListUseCase.execute()

        }, onPostExecute = {
            workspaceListLiveMutable.value = it
        })
    }

    fun getWorkspaceItemIdSelectedDefault(): Int {
        return toWorkspaceItemId(workspaceId = getWorkspaceIdSelectedUseCase.execute()) ?: 0
    }

    private fun toWorkspaceItemId(workspaceId: Long): Int? {
        if (workspaceId < 0 ||
            workspaceListLive.value == null ||
            workspaceListLive.value?.isEmpty() == true) {
            return null
        }
        for (i in workspaceListLive.value!!.indices) {
            if (workspaceListLive.value!![i].id == workspaceId) {
                return i
            }
        }
        return null
    }

    private fun toWorkspaceId(workspaceItemId: Int): Long? {
        if (workspaceItemId < 0 ||
            workspaceListLive.value == null ||
            workspaceListLive.value?.isEmpty() == true) {
            return null
        }
        return workspaceListLive.value!![workspaceItemId].id
    }

    fun createProject(workspaceItemId: Int,
                      name: String,
                      description: String? = null,
                      isPrivate: Boolean
    ) {
        if (name == "") {
            projectNameErrorResIdLiveMutable.value = R.string.error_workspace_no_name
            return
        }

        val workspaceId = toWorkspaceId(workspaceItemId = workspaceItemId)
        if (workspaceId == null) {
            workspaceSelectedErrorResIdLiveMutable.value = R.string.error_project_no_workspace
            return
        }

        viewModelScope.executeAsyncTask(
            onPreExecute = {

        }, doInBackground = {
            createProjectUseCase.execute(
                workspaceId = workspaceId,
                name = name,
                description = description,
                isPrivate = isPrivate
            )

            saveWorkspaceSectionSelectedUseCase.execute(WorkspaceSectionType.PROJECTS)
            saveWorkspaceIdSelectedUseCase.execute(workspaceId)
            saveProjectIdSelectedUseCase.execute(0L)

        }, onPostExecute = {
            isCreatedLiveMutable.value = true
        })
    }

}