package com.example.youachieve.presentation.ui.fragments.messenger

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.youachieve.R
import com.example.youachieve.databinding.FragmentCreateProjectBinding
import com.example.youachieve.databinding.FragmentMessengerBinding
import com.example.youachieve.presentation.ui.activities.main.MainViewModel
import com.example.youachieve.presentation.ui.fragments.workspace.create.viewmodels.CreateProjectViewModel

class MessengerFragment : Fragment() {

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