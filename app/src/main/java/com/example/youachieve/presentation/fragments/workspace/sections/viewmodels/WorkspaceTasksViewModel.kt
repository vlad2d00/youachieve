package com.example.youachieve.presentation.fragments.workspace.sections.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.workspace.Task
import com.example.domain.models.workspace.TaskGroup
import com.example.domain.usecase.workspace.sections.GetProjectIdSelectedUseCase
import com.example.domain.usecase.workspace.sections.GetWorkspaceIdSelectedUseCase
import com.example.domain.usecase.workspace.task.GetTaskListUseCase
import com.example.domain.usecase.workspace.task.UpdateTaskUseCase
import com.example.domain.utils.DatetimeService
import com.example.youachieve.presentation.base.BaseViewModel
import com.example.youachieve.presentation.utils.executeAsyncTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkspaceTasksViewModel @Inject constructor(
    private val getTaskListUseCase: GetTaskListUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getWorkspaceIdSelectedUseCase: GetWorkspaceIdSelectedUseCase,
    private val getProjectIdSelectedUseCase: GetProjectIdSelectedUseCase,
): BaseViewModel() {

    private var taskGroupListLiveMutable = MutableLiveData<ArrayList<TaskGroup>?>()
    val taskGroupListLive: LiveData<ArrayList<TaskGroup>?> = taskGroupListLiveMutable


    fun loadTaskList() {
        viewModelScope.executeAsyncTask(onPreExecute = {

        }, doInBackground = {
            val taskList = getTaskListUseCase.execute(
                workspaceId = getWorkspaceIdSelectedUseCase.execute(),
                projectId = getProjectIdSelectedUseCase.execute()
            )

            if (taskList == null) {
                null
            }
            else {
                getTaskGroupListByDate(taskList)
            }

        }, onPostExecute = {
            if (it == null) {
                taskGroupListLiveMutable.value = null
            }
            else {
                var taskGroupList = arrayListOf<TaskGroup>()
                if (taskGroupListLiveMutable.value != null) {
                    taskGroupList = taskGroupListLiveMutable.value!!
                }

                for (item in it) {
                    for (i in taskGroupList.indices) {
                        if (taskGroupList[i].name == item.name) {
                            taskGroupList[i].taskList.addAll(item.taskList)
                            continue
                        }
                    }
                    taskGroupList.add(item)
                }
                taskGroupListLiveMutable.value = taskGroupList
            }
        })
    }

    private fun getTaskGroupListByDate(taskList: List<Task>): List<TaskGroup> {
        val taskMap = mutableMapOf<String, ArrayList<Task>>()

        for (task in taskList) {
            var key = ""
            if (task.datetimeEnd != null) {
                key = DatetimeService.toStringFull(date = task.datetimeEnd!!.toLocalDate())
            }

            if (taskMap[key] == null) {
                taskMap[key] = arrayListOf()
            }
            taskMap[key]?.add(task)
        }

        val result = arrayListOf<TaskGroup>()
        for (item in taskMap) {
            result.add(
                TaskGroup(
                    name = item.key,
                    taskList = item.value,
                    isOpen = false
                ))
        }

        return result.toList()
    }

    fun updateStatusTask(taskId: Long, isComplete: Boolean) {
        viewModelScope.executeAsyncTask(
            onPreExecute = {

        }, doInBackground = {
            updateTaskUseCase.execute(
                taskId = taskId,
                isComplete = isComplete
            )

        }, onPostExecute = {

        })
    }

}