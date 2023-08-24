package com.example.youachieve.presentation.fragments.workspace.sections.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.workspace.Project
import com.example.domain.usecase.workspace.project.GetProjectListUseCase
import com.example.domain.usecase.workspace.sections.GetWorkspaceIdSelectedUseCase
import com.example.youachieve.presentation.base.BaseViewModel
import com.example.youachieve.presentation.utils.executeAsyncTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkspaceProjectsViewModel @Inject constructor(
    private val getProjectListUseCase: GetProjectListUseCase,
    private val getWorkspaceIdSelectedUseCase: GetWorkspaceIdSelectedUseCase,
): BaseViewModel() {

    private var projectListLiveMutable = MutableLiveData<List<Project>?>()
    val projectListLive: LiveData<List<Project>?> = projectListLiveMutable


    fun loadProjectList() {
        viewModelScope.executeAsyncTask(
            onPreExecute = {

        }, doInBackground = {
            getProjectListUseCase.execute(workspaceId = getWorkspaceIdSelectedUseCase.execute())

        }, onPostExecute = {
            projectListLiveMutable.value = it
        })
    }

}