package com.example.youachieve.presentation.ui.fragments.services

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.youachieve.databinding.FragmentServicesBinding

class ServicesFragment : Fragment() {

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