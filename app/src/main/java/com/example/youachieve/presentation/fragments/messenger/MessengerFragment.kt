package com.example.youachieve.presentation.fragments.messenger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.youachieve.databinding.FragmentMessengerBinding
import com.example.youachieve.presentation.activities.viewmodels.MainViewModel
import com.example.youachieve.presentation.base.BaseFragment

class MessengerFragment : BaseFragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMessengerBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessengerBinding.inflate(layoutInflater)

        return binding.root
    }

}