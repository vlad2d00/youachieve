package com.example.youachieve.presentation.fragments.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.youachieve.databinding.FragmentServicesBinding
import com.example.youachieve.presentation.base.BaseFragment

class ServicesFragment : BaseFragment() {

    private val viewModel: ServicesViewModel by viewModels()
    private lateinit var binding: FragmentServicesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServicesBinding.inflate(layoutInflater)

        return binding.root
    }

}