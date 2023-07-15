package com.example.youachieve.presentation.ui.fragments.workspace.sections

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.youachieve.databinding.FragmentWorkspaceTasksBinding
import com.example.youachieve.presentation.ui.fragments.workspace.sections.viewmodels.WorkspaceTasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkspaceTasksFragment : Fragment() {

    private val viewModel: WorkspaceTasksViewModel by viewModels()
    private lateinit var binding: FragmentWorkspaceTasksBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkspaceTasksBinding.inflate(layoutInflater)

        return binding.root
    }

}