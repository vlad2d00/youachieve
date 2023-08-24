package com.example.youachieve.presentation.fragments.workspace.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.youachieve.databinding.FragmentWorkspaceActionsBinding
import com.example.youachieve.presentation.fragments.workspace.sections.viewmodels.WorkspaceActionsViewModel
import com.example.youachieve.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkspaceActionsFragment : BaseFragment() {

    private val viewModel: WorkspaceActionsViewModel by viewModels()
    private lateinit var binding: FragmentWorkspaceActionsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkspaceActionsBinding.inflate(layoutInflater)

        initObserves()
        initClickListeners()

        return binding.root
    }

    private fun initObserves() {

    }

    private fun initClickListeners() {

    }

}