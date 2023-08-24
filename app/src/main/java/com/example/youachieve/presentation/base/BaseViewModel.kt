package com.example.youachieve.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.utils.LogData

open class BaseViewModel: ViewModel() {

    init {
        Log.d(LogData.TAG_APP, "${this::class.simpleName} init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(LogData.TAG_APP, "${this::class.simpleName} onCleared")
    }

}