package com.example.youachieve.presentation.ui.fragments.workspace.sections

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.youachieve.databinding.FragmentWorkspaceActionsBinding
import com.example.youachieve.presentation.ui.fragments.workspace.sections.viewmodels.WorkspaceActionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkspaceActionsFragment : Fragment() {

    private val viewModel: WorkspaceActionsViewModel by viewModels()
    private lateinit var binding: FragmentWorkspaceActionsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkspaceActionsBinding.inflate(layoutInflater)

        return binding.root
    }

}