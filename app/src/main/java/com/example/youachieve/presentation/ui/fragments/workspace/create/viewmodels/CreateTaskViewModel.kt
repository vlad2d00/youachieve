package com.example.youachieve.presentation.ui.fragments.workspace.create.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Project
import com.example.domain.models.User
import com.example.domain.models.Workspace
import com.example.domain.models.WorkspaceSectionType
import com.example.domain.usecase.workspace.project.GetProjectListUseCase
import com.example.domain.usecase.workspace.sections.SaveProjectIdSelectedUseCase
import com.example.domain.usecase.workspace.sections.SaveWorkspaceSectionSelectedUseCase
import com.example.domain.usecase.workspace.task.CreateTaskUseCase
import com.example.domain.usecase.workspace.workspace.GetWorkspaceListUseCase
import com.example.youachieve.R
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
    private val saveProjectIdSelectedUseCase: SaveProjectIdSelectedUseCase,
): ViewModel()  {

    var workspaceSelectedItemId = 0L
    var projectSelectedItemId = 0L
    var taskName = ""
    var taskDescription = ""
    var taskDatetimeBegin: LocalDateTime? = null
    var taskDatetimeEnd: LocalDateTime? = null
    var taskUsers = emptyArray<User>()

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

    fun loadProjectList(workspaceItemId: Long) {
        val workspaceId = toWorkspaceId(workspaceItemId)
        val projectListSaved = projectMap[workspaceId]

        if (projectListSaved == null) {
            viewModelScope.executeAsyncTask(onPreExecute = {

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

    private fun toWorkspaceId(workspaceItemId: Long): Long? {
        if (workspaceItemId <= 0) {
            return null
        }
        if (workspaceListLive.value?.isEmpty() == true) {
            return null
        }
        return workspaceListLive.value!![workspaceItemId.toInt()].id
    }

    private fun toProjectId(projectItemId: Long): Long? {
        if (projectItemId <= 0) {
            return null
        }
        if (projectListLive.value?.isEmpty() == true) {
            return null
        }
        return projectListLive.value!![projectItemId.toInt()].id
    }

    fun createTask(workspaceItemId: Long,
                   projectItemId: Long,
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

        val workspaceId = toWorkspaceId(workspaceItemId)
        if (workspaceId == null) {
            workspaceSelectedErrorResIdLiveMutable.value = R.string.error_task_no_workspace
            return
        }
        workspaceSelectedErrorResIdLiveMutable.value = null

        val projectId = toProjectId(projectItemId)
        if (projectId == null) {
            projectSelectedErrorResIdLiveMutable.value = R.string.error_task_no_project
            return
        }
        projectSelectedErrorResIdLiveMutable.value = null

        viewModelScope.executeAsyncTask(onPreExecute = {

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
            saveProjectIdSelectedUseCase.execute(projectId)

        }, onPostExecute = {
            isCreatedLiveMutable.value = true
        })
    }

}