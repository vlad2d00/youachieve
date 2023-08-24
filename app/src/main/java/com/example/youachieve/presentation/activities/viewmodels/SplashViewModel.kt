package com.example.youachieve.presentation.activities.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.authorization.GetAuthorizationUserIdUseCase
import com.example.domain.usecase.authorization.GetAuthorizationUserTokenUseCase
import com.example.youachieve.presentation.base.BaseViewModel
import com.example.youachieve.presentation.utils.executeAsyncTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor (
    private val getAuthorizationUserIdUseCase: GetAuthorizationUserIdUseCase,
    private val getAuthorizationUserTokenUseCase: GetAuthorizationUserTokenUseCase,
): BaseViewModel() {

    private var isAuthorizedLiveMutable = MutableLiveData<Boolean?>()
    val isAuthorizedLive: LiveData<Boolean?> = isAuthorizedLiveMutable


    fun authorization() {
        viewModelScope.executeAsyncTask(onPreExecute = {

        }, doInBackground = {
            val userId = getAuthorizationUserIdUseCase.execute()
            val userToken = getAuthorizationUserTokenUseCase.execute()
            Pair(userId, userToken)

        }, onPostExecute = {
            isAuthorizedLiveMutable.value = it.first != 0L && it.second != ""
        })
    }

}