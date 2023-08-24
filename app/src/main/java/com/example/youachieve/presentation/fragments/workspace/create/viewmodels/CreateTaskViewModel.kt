package com.example.youachieve.presentation.fragments.workspace.create.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.workspace.Project
import com.example.domain.models.workspace.Workspace
import com.example.domain.models.workspace.WorkspaceSectionType
import com.example.domain.usecase.workspace.project.GetProjectListUseCase
import com.example.domain.usecase.workspace.sections.*
import com.example.domain.usecase.workspace.task.CreateTaskUseCase
import com.example.domain.usecase.workspace.workspace.GetWorkspaceListUseCase
import com.example.youachieve.R
import com.example.youachieve.presentation.base.BaseViewModel
import com.example.youachieve.presentation.utils.executeAsyncTask
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase,
    private val getWorkspaceListUseCase: GetWorkspaceListUseCase,
    private val getProjectListUseCase: GetProjectListUseCase,
    private val saveWorkspaceSectionSelectedUseCase: SaveWorkspaceSectionSelectedUseCase,
    private val saveWorkspaceIdSelectedUseCase: SaveWorkspaceIdSelectedUseCase,
    private val saveProjectIdSelectedUseCase: SaveProjectIdSelectedUseCase,
    private val getWorkspaceIdSelectedUseCase: GetWorkspaceIdSelectedUseCase,
    private val getProjectIdSelectedUseCase: GetProjectIdSelectedUseCase,
): BaseViewModel()  {

    private var projectMap = mutableMapOf<Long, List<Project>>()

    private var workspaceListLiveMutable = MutableLiveData<List<Workspace>?>()
    val workspaceListLive: LiveData<List<Workspace>?> = workspaceListLiveMutable

    private var workspaceSelectedErrorResIdLiveMutable = MutableLiveData<Int?>()
    val workspaceSelectedErrorResIdLive: LiveData<Int?> = workspaceSelectedErrorResIdLiveMutable

    private var projectListLiveMutable = MutableLiveData<List<Project>?>()
    val projectListLive: LiveData<List<Project>?> = projectListLiveMutable

    private var projectSelectedErrorResIdLiveMutable = MutableLiveData<Int?>()
    val projectSelectedErrorResIdLive: LiveData<Int?> = projectSelectedErrorResIdLiveMutable

    private var taskNameErrorResIdLiveMutable = MutableLiveData<Int?>()
    val taskNameErrorResIdLive: LiveData<Int?> = taskNameErrorResIdLiveMutable

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

    fun loadProjectList(workspaceItemId: Int) {
        val workspaceId = toWorkspaceId(workspaceItemId)
        val projectListSaved = projectMap[workspaceId]

        if (projectListSaved == null) {
            viewModelScope.executeAsyncTask(
                onPreExecute = {

            }, doInBackground = {
                var projectList: List<Project>? = null
                if (workspaceId != null) {
                    projectList = getProjectListUseCase.execute(workspaceId = workspaceId)
                }
                Pair(workspaceId, projectList)

            }, onPostExecute = {
                if (it.first != null && it.second != null) {
                    projectMap[it.first!!] = it.second!!
                }
                projectListLiveMutable.value = it.second
            })
        }
        else {
            projectListLiveMutable.value = projectListSaved
        }
    }

    fun getWorkspaceItemIdSelectedDefault(): Int {
        return toWorkspaceItemId(workspaceId = getWorkspaceIdSelectedUseCase.execute()) ?: 0
    }

    fun getProjectItemIdSelectedDefault(): Int {
        return toProjectItemId(projectId = getProjectIdSelectedUseCase.execute()) ?: 0
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

    private fun toProjectItemId(projectId: Long): Int? {
        if (projectId < 0 ||
            projectListLive.value == null ||
            projectListLive.value?.isEmpty() == true) {
            return null
        }
        for (i in projectListLive.value!!.indices) {
            if (projectListLive.value!![i].id == projectId) {
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

    private fun toProjectId(projectItemId: Int): Long? {
        if (projectItemId < 0 ||
            projectListLive.value == null ||
            projectListLive.value?.isEmpty() == true) {
            return null
        }
        return projectListLive.value!![projectItemId].id
    }

    fun createTask(workspaceItemId: Int,
                   projectItemId: Int,
                   name: String,
                   description: String? = null,
                   imageCoverName: String? = null,
                   datetimeBegin: LocalDateTime? = null,
                   datetimeEnd: LocalDateTime? = null
    ) {
        if (name == "") {
            taskNameErrorResIdLiveMutable.value = R.string.error_task_no_name
            return
        }
        taskNameErrorResIdLiveMutable.value = null

        val workspaceId = toWorkspaceId(workspaceItemId = workspaceItemId)
        if (workspaceId == null) {
            workspaceSelectedErrorResIdLiveMutable.value = R.string.error_task_no_workspace
            return
        }
        workspaceSelectedErrorResIdLiveMutable.value = null

        val projectId = toProjectId(projectItemId = projectItemId)
        if (projectId == null) {
            projectSelectedErrorResIdLiveMutable.value = R.string.error_task_no_project
            return
        }
        projectSelectedErrorResIdLiveMutable.value = null

        viewModelScope.executeAsyncTask(
            onPreExecute = {

        }, doInBackground = {
            createTaskUseCase.execute(
                projectId = projectId,
                name = name,
                description = description,
                imageCoverName = imageCoverName,
                datetimeBegin = datetimeBegin,
                datetimeEnd = datetimeEnd
            )

            saveWorkspaceSectionSelectedUseCase.execute(WorkspaceSectionType.TASKS)
            saveWorkspaceIdSelectedUseCase.execute(workspaceId)
            saveProjectIdSelectedUseCase.execute(projectId)

        }, onPostExecute = {
            isCreatedLiveMutable.value = true
        })
    }

}