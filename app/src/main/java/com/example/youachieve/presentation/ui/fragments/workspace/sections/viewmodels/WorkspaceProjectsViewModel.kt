package com.example.youachieve.presentation.ui.fragments.workspace.sections.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Project
import com.example.domain.models.Workspace
import com.example.domain.usecase.workspace.project.GetProjectListUseCase
import com.example.domain.usecase.workspace.sections.GetWorkspaceIdSelectedUseCase
import com.example.youachieve.presentation.utils.executeAsyncTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkspaceProjectsViewModel @Inject constructor(
    private val getProjectListUseCase: GetProjectListUseCase,
    private val getWorkspaceIdSelectedUseCase: GetWorkspaceIdSelectedUseCase
): ViewModel() {

    private var projectListLiveMutable = MutableLiveData<List<Project>?>()
    val projectListLive: LiveData<List<Project>?> = projectListLiveMutable


    fun loadProjectList() {
        viewModelScope.executeAsyncTask(onPreExecute = {

        }, doInBackground = {
            getProjectListUseCase.execute(workspaceId = getWorkspaceIdSelectedUseCase.execute())

        }, onPostExecute = {
            projectListLiveMutable.value = it
        })
    }

}