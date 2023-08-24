package com.example.youachieve.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.youachieve.databinding.ActivityMainBinding
import com.example.youachieve.databinding.ActivitySplashBinding
import com.example.youachieve.presentation.activities.viewmodels.SplashViewModel
import com.example.youachieve.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isAuthorizedLive.observe(this) {
            if (viewModel.isAuthorizedLive.value != null) {
                startActivity(viewModel.isAuthorizedLive.value!!)
            }
        }
        viewModel.authorization()
    }

    private fun startActivity(isAuthorized: Boolean) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}