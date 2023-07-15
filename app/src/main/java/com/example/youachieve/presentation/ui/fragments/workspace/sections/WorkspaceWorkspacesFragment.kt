package com.example.youachieve.presentation.ui.fragments.workspace.sections
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.Workspace
import com.example.youachieve.R
import com.example.youachieve.databinding.FragmentWorkspaceWorkspacesBinding
import com.example.youachieve.presentation.adapters.WorkspaceAdapter
import com.example.youachieve.presentation.adapters.listeners.WorkspaceActionListener
import com.example.youachieve.presentation.ui.fragments.workspace.main.WorkspaceFragment
import com.example.youachieve.presentation.ui.fragments.workspace.sections.viewmodels.WorkspaceWorkspacesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkspaceWorkspacesFragment : Fragment() {

    private val viewModel: WorkspaceWorkspacesViewModel by viewModels()
    private lateinit var binding: FragmentWorkspaceWorkspacesBinding
    private lateinit var adapter: WorkspaceAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkspaceWorkspacesBinding.inflate(layoutInflater)
        initWorkspaceAdapter()

        viewModel.workspaceListLive.observe(viewLifecycleOwner) {
            updateMessageStatus()
        }

        viewModel.loadWorkspaceList()

        return binding.root
    }

    private fun initWorkspaceAdapter() {
        adapter = WorkspaceAdapter(object : WorkspaceActionListener {
            override fun onSelect(workspace: Workspace) {
                val fragment = parentFragmentManager.findFragmentById(R.id.mainSectionsFragment)
                (fragment as WorkspaceFragment).switchToWorkspace(
                    workspaceId = workspace.id,
                    fragmentManager = parentFragmentManager
                )
            }

            override fun onSettings(workspace: Workspace) {
                // TODO Реализовать, как будет окно редактирования рабочего пространства
            }
        })

        binding.workspaceWorkspacesRecyclerView.layoutManager =
            GridLayoutManager(binding.root.context, 2)
        binding.workspaceWorkspacesRecyclerView.adapter = adapter
    }

    private fun updateMessageStatus() {
        if (viewModel.workspaceListLive.value != null) {
            if (viewModel.workspaceListLive.value!!.isEmpty()) {
                binding.workspaceWorkspacesTextStatus.isVisible = true
                binding.workspaceWorkspacesTextStatus.setText(R.string.workspace_list_is_empty)
            }
            else {
                binding.workspaceWorkspacesTextStatus.isVisible = false
                adapter.data = viewModel.workspaceListLive.value!!
            }
        }
        else {
            binding.workspaceWorkspacesTextStatus.isVisible = true
            binding.workspaceWorkspacesTextStatus.setText(R.string.error_load)
        }
    }

}
