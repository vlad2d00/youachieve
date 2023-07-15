package com.example.youachieve.presentation.ui.fragments.workspace.create.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.WorkspaceSectionType
import com.example.domain.usecase.workspace.sections.SaveWorkspaceIdSelectedUseCase
import com.example.domain.usecase.workspace.sections.SaveWorkspaceSectionSelectedUseCase
import com.example.domain.usecase.workspace.workspace.CreateWorkspaceUseCase
import com.example.youachieve.R
import com.example.youachieve.presentation.utils.executeAsyncTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateWorkspaceViewModel @Inject constructor(
    private val createWorkspaceUseCase: CreateWorkspaceUseCase,
    private val saveWorkspaceSectionSelectedUseCase: SaveWorkspaceSectionSelectedUseCase,
    private val saveWorkspaceIdSelectedUseCase: SaveWorkspaceIdSelectedUseCase,
): ViewModel()  {

    var workspaceName = ""
    var workspaceDescription = ""
    var workspaceIsPrivate = false

    private var workspaceNameErrorResIdLiveMutable = MutableLiveData<Int?>()
    val workspaceNameErrorResIdLive: LiveData<Int?> = workspaceNameErrorResIdLiveMutable

    private var isCreatedLiveMutable = MutableLiveData<Boolean>(false)
    val isCreatedLive: LiveData<Boolean> = isCreatedLiveMutable

    fun createWorkspace() {
        if (workspaceName == "") {
            workspaceNameErrorResIdLiveMutable.value = R.string.error_workspace_no_name
            return
        }

        viewModelScope.executeAsyncTask(onPreExecute = {

        }, doInBackground = {
            createWorkspaceUseCase.execute(
                name = workspaceName,
                description = workspaceDescription,
                isPrivate = workspaceIsPrivate
            )

            saveWorkspaceSectionSelectedUseCase.execute(WorkspaceSectionType.WORKSPACES)
            saveWorkspaceIdSelectedUseCase.execute(0L)

        }, onPostExecute = {
            isCreatedLiveMutable.value = true
        })
    }

}