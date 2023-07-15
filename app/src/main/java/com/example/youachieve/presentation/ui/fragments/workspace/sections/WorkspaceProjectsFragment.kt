package com.example.youachieve.presentation.ui.fragments.workspace.sections

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.models.Project
import com.example.youachieve.R
import com.example.youachieve.databinding.FragmentWorkspaceProjectsBinding
import com.example.youachieve.presentation.adapters.ProjectAdapter
import com.example.youachieve.presentation.adapters.listeners.ProjectActionListener
import com.example.youachieve.presentation.ui.fragments.workspace.main.WorkspaceFragment
import com.example.youachieve.presentation.ui.fragments.workspace.sections.viewmodels.WorkspaceProjectsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkspaceProjectsFragment : Fragment() {

    private val viewModel: WorkspaceProjectsViewModel by viewModels()
    private lateinit var binding: FragmentWorkspaceProjectsBinding
    private lateinit var adapter: ProjectAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkspaceProjectsBinding.inflate(layoutInflater)
        initProjectAdapter()
        
        viewModel.projectListLive.observe(viewLifecycleOwner, Observer {
            updateHeaderText()
        })

        viewModel.loadProjectList()

        return binding.root
    }

    private fun initProjectAdapter() {
        adapter = ProjectAdapter(object : ProjectActionListener {
            override fun onSelect(project: Project) {
                val fragment = parentFragmentManager.findFragmentById(R.id.mainSectionsFragment)
                (fragment as WorkspaceFragment).switchToProject(
                    projectId = project.id,
                    fragmentManager = parentFragmentManager
                )
            }

            override fun onSettings(project: Project) {
                // TODO Реализовать, как будет окно редактирования проекта
            }
        })

        binding.workspaceProjectsRecyclerView.layoutManager =
            GridLayoutManager(binding.root.context, 2)
        binding.workspaceProjectsRecyclerView.adapter = adapter
    }

    private fun updateHeaderText() {
        if (viewModel.projectListLive.value != null) {
            if (viewModel.projectListLive.value!!.isEmpty()) {
                binding.workspaceProjectsTextStatus.isVisible = true
                binding.workspaceProjectsTextStatus.setText(R.string.project_list_is_empty)
            }
            else {
                binding.workspaceProjectsTextStatus.isVisible = false
                adapter.data = viewModel.projectListLive.value!!
            }
        }
        else {
            binding.workspaceProjectsTextStatus.isVisible = true
            binding.workspaceProjectsTextStatus.setText(R.string.error_load)
        }
    }

}