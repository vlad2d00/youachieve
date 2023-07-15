package com.example.youachieve.presentation.ui.fragments.workspace.sections

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.youachieve.databinding.FragmentWorkspaceUsersBinding
import com.example.youachieve.presentation.ui.fragments.workspace.sections.viewmodels.WorkspaceUsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkspaceUsersFragment : Fragment() {

    private val viewModel: WorkspaceUsersViewModel by viewModels()
    private lateinit var binding: FragmentWorkspaceUsersBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkspaceUsersBinding.inflate(layoutInflater)

        return binding.root
    }

}