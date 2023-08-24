package com.example.youachieve.presentation.base

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.domain.utils.LogData

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LogData.TAG_APP, "${this::class.simpleName} onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LogData.TAG_APP, "${this::class.simpleName} onDestroy")
    }

}