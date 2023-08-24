package com.example.youachieve.presentation.fragments.workspace.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.workspace.WorkspaceSection
import com.example.youachieve.R
import com.example.youachieve.databinding.FragmentWorkspaceSectionsBinding
import com.example.youachieve.presentation.adapters.WorkspaceSectionAdapter
import com.example.youachieve.presentation.adapters.decorators.GridSpacingItemDecoration
import com.example.youachieve.presentation.adapters.listeners.WorkspaceSectionActionListener
import com.example.youachieve.presentation.fragments.workspace.main.viewmodels.WorkspaceSectionsViewModel
import com.example.youachieve.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkspaceSectionsFragment : BaseFragment() {

    private val viewModel: WorkspaceSectionsViewModel by viewModels()
    private lateinit var binding: FragmentWorkspaceSectionsBinding
    private lateinit var workspaceSectionAdapter: WorkspaceSectionAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkspaceSectionsBinding.inflate(layoutInflater)

        initObserves()
        initClickListeners()
        initWorkspaceSectionAdapter()

        if (savedInstanceState == null) {
            viewModel.loadSection(
                sectionTargetType = viewModel.getSectionSelected(),
                isAnimate = false,
                fragmentManager = parentFragmentManager)
        }

        return binding.root
    }

    private fun initObserves() {

        viewModel.sectionListLive.observe(viewLifecycleOwner) {
            if (viewModel.sectionListLive.value != null) {
                workspaceSectionAdapter.data = viewModel.sectionListLive.value!!
            }
        }

    }

    private fun initClickListeners() {

    }

    private fun initWorkspaceSectionAdapter() {
        workspaceSectionAdapter = WorkspaceSectionAdapter(object : WorkspaceSectionActionListener {
            override fun onSelect(workspaceSection: WorkspaceSection) {
                viewModel.loadSection(
                    sectionTargetType = workspaceSection.type,
                    isAnimate = true,
                    fragmentManager = parentFragmentManager
                )
            }
        })

        val spanCount = 1
        val layoutManager = LinearLayoutManager(
            binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        val itemDecoration = GridSpacingItemDecoration(
            spanCount = spanCount,
            orientation = GridLayoutManager.HORIZONTAL,
            spacing = resources.getDimension(R.dimen.indent_normal).toInt(),
            includeEdge = true
        )

        binding.workspaceSectionsRecyclerView.layoutManager = layoutManager
        binding.workspaceSectionsRecyclerView.addItemDecoration(itemDecoration)
        binding.workspaceSectionsRecyclerView.adapter = workspaceSectionAdapter
    }

}