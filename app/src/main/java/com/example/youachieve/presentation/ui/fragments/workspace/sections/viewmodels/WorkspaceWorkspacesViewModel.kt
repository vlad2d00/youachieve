package com.example.youachieve.presentation.ui.fragments.workspace.sections.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Workspace
import com.example.domain.usecase.workspace.workspace.GetWorkspaceListUseCase
import com.example.domain.usecase.workspace.workspace.GetWorkspaceUseCase
import com.example.youachieve.presentation.utils.executeAsyncTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkspaceWorkspacesViewModel @Inject constructor(
    private val getWorkspaceListUseCase: GetWorkspaceListUseCase
) : ViewModel() {

    private var workspaceListLiveMutable = MutableLiveData<List<Workspace>?>()
    val workspaceListLive: LiveData<List<Workspace>?> = workspaceListLiveMutable


    fun loadWorkspaceList() {
        viewModelScope.executeAsyncTask(onPreExecute = {

        }, doInBackground = {
            getWorkspaceListUseCase.execute()

        }, onPostExecute = {
            workspaceListLiveMutable.value = it
        })
    }

}