package com.example.youachieve.presentation.activities

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.activity.viewModels
import com.example.youachieve.R
import com.example.youachieve.databinding.ActivityMainBinding
import com.example.youachieve.presentation.activities.viewmodels.MainViewModel
import com.example.youachieve.presentation.fragments.main.MainSectionsFragment
import com.example.youachieve.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    companion object {
        private var isInit = false
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("YouAchieve", "==================== MAIN_ACTIVITY START ====================")
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!isInit) {
            saveDisplaySize()
            viewModel.testDatabase()
        }
        isInit = true

        if (savedInstanceState == null) {
            val fragment = MainSectionsFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.mainFragment, fragment).commit()
        }
    }

    private fun saveDisplaySize() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        viewModel.saveDisplaySize(
            width = displayMetrics.widthPixels.toFloat().toInt(),
            height = displayMetrics.heightPixels.toFloat().toInt()
        )
    }

}