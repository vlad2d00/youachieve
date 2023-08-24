package com.example.youachieve.presentation.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.utils.LogData
import com.example.youachieve.presentation.utils.OnBackPressedListener
import kotlin.system.exitProcess

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LogData.TAG_APP, "${this::class.simpleName} onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LogData.TAG_APP, "${this::class.simpleName} onDestroy")
    }

    override fun onBackPressed() {
        var backPressedListener: OnBackPressedListener? = null

        for (fragment in supportFragmentManager.fragments) {
            if (fragment is OnBackPressedListener) {
                backPressedListener = fragment
                break
            }
        }

        var result = false
        if (backPressedListener != null) {
            result = backPressedListener.onBackPressed()
        }
        if (!result) {
            moveTaskToBack(true);
            exitProcess(-1)
//            super.onBackPressed()
        }
    }

}