package com.example.youachieve.presentation.ui.fragments.workspace.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.WorkspaceSection
import com.example.youachieve.databinding.FragmentWorkspaceSectionsBinding
import com.example.youachieve.presentation.adapters.WorkspaceSectionAdapter
import com.example.youachieve.presentation.adapters.listeners.WorkspaceSectionActionListener
import com.example.youachieve.presentation.ui.fragments.workspace.main.viewmodels.WorkspaceSectionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkspaceSectionsFragment : Fragment() {

    private val viewModel: WorkspaceSectionsViewModel by viewModels()
    private lateinit var binding: FragmentWorkspaceSectionsBinding
    private lateinit var adapter: WorkspaceSectionAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkspaceSectionsBinding.inflate(layoutInflater)
        initWorkspaceSectionAdapter()

        viewModel.sectionListLive.observe(viewLifecycleOwner) {
            if (viewModel.sectionListLive.value != null) {
                adapter.data = viewModel.sectionListLive.value!!
            }
        }

        viewModel.loadSection(
            workspaceSectionType = viewModel.getSectionSelected(),
            isAnimate = false,
            fragmentManager = parentFragmentManager)

        return binding.root
    }

    private fun initWorkspaceSectionAdapter() {
        adapter = WorkspaceSectionAdapter(object : WorkspaceSectionActionListener {
            override fun onSelect(workspaceSection: WorkspaceSection) {
                viewModel.loadSection(
                    workspaceSectionType = workspaceSection.type,
                    isAnimate = true,
                    fragmentManager = parentFragmentManager
                )
            }
        })

        binding.workspaceSectionsRecyclerView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.workspaceSectionsRecyclerView.adapter = adapter
    }

}