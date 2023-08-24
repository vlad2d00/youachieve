package com.example.youachieve.presentation.fragments.workspace.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.workspace.Project
import com.example.youachieve.R
import com.example.youachieve.databinding.FragmentWorkspaceProjectsBinding
import com.example.youachieve.presentation.adapters.ProjectAdapter
import com.example.youachieve.presentation.adapters.decorators.GridSpacingItemDecoration
import com.example.youachieve.presentation.adapters.listeners.ProjectActionListener
import com.example.youachieve.presentation.fragments.workspace.main.WorkspaceFragment
import com.example.youachieve.presentation.fragments.workspace.sections.viewmodels.WorkspaceProjectsViewModel
import com.example.youachieve.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkspaceProjectsFragment : BaseFragment() {

    private val viewModel: WorkspaceProjectsViewModel by viewModels()
    private lateinit var binding: FragmentWorkspaceProjectsBinding
    private lateinit var projectAdapter: ProjectAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkspaceProjectsBinding.inflate(layoutInflater)
        initProjectAdapter()

        initObserves()
        initClickListeners()

        if (savedInstanceState == null) {
            viewModel.loadProjectList()
        }

        return binding.root
    }

    private fun initObserves() {

        viewModel.projectListLive.observe(viewLifecycleOwner) {
            updateMessageText()
            if (viewModel.projectListLive.value != null) {
                projectAdapter.data = viewModel.projectListLive.value!!
            }
        }

    }

    private fun initClickListeners() {

    }

    private fun initProjectAdapter() {
        projectAdapter = ProjectAdapter(object : ProjectActionListener {

            override fun onSelect(project: Project) {
                val fragment = parentFragmentManager.findFragmentById(R.id.mainSectionsFragment)
                (fragment as WorkspaceFragment).switchToProject(
                    workspaceId = project.workspaceId,
                    projectId = project.id,
                    fragmentManager = parentFragmentManager
                )
            }

            override fun onSettings(project: Project) {
                // TODO Реализовать позднее
                Toast.makeText(binding.root.context,
                    "Перейти в настройки проекта пока невозможно", Toast.LENGTH_SHORT).show()
            }

        })

        val spanCount = 2
        val layoutManager = GridLayoutManager(
            binding.root.context, spanCount, GridLayoutManager.VERTICAL, false)
        val itemDecoration = GridSpacingItemDecoration(
            spanCount = spanCount,
            spacing = resources.getDimension(R.dimen.indent_normal).toInt(),
            orientation = LinearLayoutManager.VERTICAL,
            includeEdge = true
        )

        binding.workspaceProjectsRecyclerView.layoutManager = layoutManager
        binding.workspaceProjectsRecyclerView.addItemDecoration(itemDecoration)
        binding.workspaceProjectsRecyclerView.adapter = projectAdapter
    }

    private fun updateMessageText() {
        if (viewModel.projectListLive.value != null) {
            if (viewModel.projectListLive.value!!.isEmpty()) {
                binding.workspaceProjectsTextStatus.isVisible = true
                binding.workspaceProjectsTextStatus.setText(R.string.project_list_is_empty)
            }
            else {
                binding.workspaceProjectsTextStatus.isVisible = false
                binding.workspaceProjectsTextStatus.setText(R.string.loading)
            }
        }
        else {
            binding.workspaceProjectsTextStatus.isVisible = true
            binding.workspaceProjectsTextStatus.setText(R.string.error_load)
        }
    }

}