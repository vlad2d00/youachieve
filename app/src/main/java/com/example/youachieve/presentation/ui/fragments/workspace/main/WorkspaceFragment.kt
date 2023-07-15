package com.example.youachieve.presentation.ui.fragments.workspace.main

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.domain.models.WorkspaceEntityType
import com.example.domain.models.WorkspaceSectionCategory
import com.example.youachieve.R
import com.example.youachieve.databinding.FragmentWorkspaceBinding
import com.example.youachieve.presentation.ui.fragments.workspace.create.CreateProjectFragment
import com.example.youachieve.presentation.ui.fragments.workspace.create.CreateTaskFragment
import com.example.youachieve.presentation.ui.fragments.workspace.create.CreateWorkspaceFragment
import com.example.youachieve.presentation.ui.fragments.workspace.main.viewmodels.WorkspaceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkspaceFragment : Fragment() {

    private val viewModel: WorkspaceViewModel by viewModels()
    private lateinit var binding: FragmentWorkspaceBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkspaceBinding.inflate(layoutInflater)

        viewModel.workspaceSectionLive.observe(viewLifecycleOwner) {
            updateHeader()
        }

        viewModel.headerTextLive.observe(viewLifecycleOwner) {
            updateHeader()
        }

        binding.workspaceButtonMenu.setOnClickListener {
            viewModel.switchToHigherLevel(parentFragmentManager)
        }

        binding.workspaceButtonAdd.setOnClickListener {
            showPopupMenuAddEntity(it)
        }

        viewModel.updateHeader()
        viewModel.loadFragment(parentFragmentManager)

        return binding.root
    }

    fun switchToWorkspace(workspaceId: Long, fragmentManager: FragmentManager) {
        viewModel.switchToWorkspace(workspaceId = workspaceId, fragmentManager = fragmentManager)
    }

    fun switchToProject(projectId: Long, fragmentManager: FragmentManager) {
        viewModel.switchToProject(projectId = projectId, fragmentManager = fragmentManager)
    }

    private fun goToFragmentCreateWorkspaceEntity(workspaceEntityType: WorkspaceEntityType) {
        parentFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_from_right,
                R.anim.disappearance_small
            )
            replace(R.id.mainFragment, when(workspaceEntityType) {
                WorkspaceEntityType.WORKSPACE -> CreateWorkspaceFragment()
                WorkspaceEntityType.PROJECT -> CreateProjectFragment()
                WorkspaceEntityType.TASK -> CreateTaskFragment()
            })
        }
    }

    private fun showPopupMenuAddEntity(view: View) {
        val popupMenu = PopupMenu(binding.root.context, view)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuWorkspaceAddWorkspace -> {
                    goToFragmentCreateWorkspaceEntity(workspaceEntityType = WorkspaceEntityType.WORKSPACE)
                    true
                }
                R.id.menuWorkspaceAddProject -> {
                    goToFragmentCreateWorkspaceEntity(workspaceEntityType = WorkspaceEntityType.PROJECT)
                    true
                }
                R.id.menuWorkspaceAddTask -> {
                    goToFragmentCreateWorkspaceEntity(workspaceEntityType = WorkspaceEntityType.TASK)
                    true
                }
                else -> false
            }
        }

        binding.workspaceButtonAddImage.rotation = 45F
        popupMenu.setOnDismissListener {
            binding.workspaceButtonAddImage.rotation = 0F
        }

        popupMenu.gravity = Gravity.START

        popupMenu.inflate(R.menu.menu_workspace_add)
        popupMenu.show()
    }

    private fun updateHeader() {
        if (viewModel.workspaceSectionLive.value != null) {
            val image = when (viewModel.workspaceSectionLive.value) {
                WorkspaceSectionCategory.WORKSPACES_ALL -> R.drawable.workspaces
                WorkspaceSectionCategory.WORKSPACE -> R.drawable.workspace
                WorkspaceSectionCategory.PROJECT -> R.drawable.project
                else -> R.drawable.question
            }
            binding.workspaceImageCategory.setImageResource(image)

            if (viewModel.workspaceSectionLive.value == WorkspaceSectionCategory.WORKSPACES_ALL) {
                binding.workspaceTextHeader.setText(R.string.workspace_all)
            }
        }

        if (viewModel.headerTextLive.value != null) {
            binding.workspaceTextHeader.text = viewModel.headerTextLive.value
        }
        else if (viewModel.workspaceSectionLive.value != WorkspaceSectionCategory.WORKSPACES_ALL) {
            binding.workspaceTextHeader.setText(R.string.error_load)
        }
    }

}