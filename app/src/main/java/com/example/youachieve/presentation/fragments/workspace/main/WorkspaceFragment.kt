package com.example.youachieve.presentation.fragments.workspace.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.workspace.Project
import com.example.domain.models.workspace.Workspace
import com.example.domain.models.workspace.WorkspaceEntityType
import com.example.domain.models.workspace.WorkspaceSectionCategory
import com.example.youachieve.R
import com.example.youachieve.databinding.FragmentWorkspaceBinding
import com.example.youachieve.presentation.adapters.WorkspaceAdapter
import com.example.youachieve.presentation.adapters.listeners.WorkspaceActionListener
import com.example.youachieve.presentation.fragments.workspace.create.CreateProjectFragment
import com.example.youachieve.presentation.fragments.workspace.create.CreateTaskFragment
import com.example.youachieve.presentation.fragments.workspace.create.CreateWorkspaceFragment
import com.example.youachieve.presentation.fragments.workspace.main.viewmodels.WorkspaceViewModel
import com.example.youachieve.presentation.base.BaseFragment
import com.example.youachieve.presentation.utils.OnBackPressedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkspaceFragment : BaseFragment(), OnBackPressedListener {

    private val viewModel: WorkspaceViewModel by viewModels()
    private lateinit var binding: FragmentWorkspaceBinding
    private lateinit var workspaceAdapter: WorkspaceAdapter
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkspaceBinding.inflate(layoutInflater)

        initObserves()
        initClickListeners()
        initWorkspaceAdapter()

        initNavigationDrawer()
        normalizeHeaderTextWidth()

        if (savedInstanceState == null) {
            viewModel.loadWorkspaceList()
            viewModel.loadSection(parentFragmentManager)
            viewModel.updateHeader()
        }

        return binding.root
    }

    private fun initObserves() {
        viewModel.workspaceSectionLive.observe(viewLifecycleOwner) {
            updateHeader()
        }

        viewModel.headerTextLive.observe(viewLifecycleOwner) {
            updateHeader()
        }

        viewModel.workspaceListLive.observe(viewLifecycleOwner) {
            updateTextCount()
            if (viewModel.workspaceListLive.value != null) {
                workspaceAdapter.data = viewModel.workspaceListLive.value!!
            }
        }
    }

    private fun initClickListeners() {

        binding.workspaceButtonMenu.setOnClickListener {
            openNavigationDrawer()
        }

        binding.workspaceButtonSearch.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Осуществлять поиск пока невозможно", Toast.LENGTH_SHORT).show()
        }

        binding.workspaceButtonNotification.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Просматривать уведомления пока невозможно", Toast.LENGTH_SHORT).show()
        }

        binding.workspaceButtonOther.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Переходить в настройки пока невозможно", Toast.LENGTH_SHORT).show()
        }

        binding.workspaceButtonPresentation.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Менять предсталение пространств пока невозможно", Toast.LENGTH_SHORT).show()
        }

        binding.workspaceButtonAdd.setOnClickListener {
            showPopupMenuAddEntity(it)
        }

        binding.workspaceNavigationButtonAddWorkspace.setOnClickListener {
            goToFragmentCreateWorkspaceEntity(workspaceEntityType = WorkspaceEntityType.WORKSPACE)
        }

        binding.workspaceNavigationButtonWorkspacesAll.setOnClickListener {
            closeNavigationDrawer()
            switchToWorkspace(workspaceId = 0L, fragmentManager = parentFragmentManager)
        }

        binding.workspaceNavigationButtonInvitations.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Перейти на страницу приглашений пока невозможно", Toast.LENGTH_SHORT).show()
        }

        binding.workspaceNavigationImageAvatar.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Перейти на страницу пользователя пока невозможно", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initWorkspaceAdapter() {
        workspaceAdapter = WorkspaceAdapter(object : WorkspaceActionListener {

            override fun onSelect(workspace: Workspace) {
                closeNavigationDrawer()

                val fragment = parentFragmentManager.findFragmentById(R.id.mainSectionsFragment)
                (fragment as WorkspaceFragment).switchToWorkspace(
                    workspaceId = workspace.id,
                    fragmentManager = parentFragmentManager
                )
            }

            override fun onSettings(project: Project) {
                // TODO Реализовать позднее
                Toast.makeText(binding.root.context,
                    "Перейти в настройки пространства пока невозможно", Toast.LENGTH_SHORT).show()
            }
        })

        val layoutManager = LinearLayoutManager(
            binding.root.context, GridLayoutManager.VERTICAL, false)

        binding.workspaceNavigationWorkspacesRecyclerView.layoutManager = layoutManager
        binding.workspaceNavigationWorkspacesRecyclerView.adapter = workspaceAdapter
    }

    override fun onBackPressed(): Boolean {
        var ret = closeNavigationDrawer()
        if (!ret) {
            ret = viewModel.switchToHigherLevel(fragmentManager = parentFragmentManager)
        }
        return ret
    }

    fun switchToWorkspace(workspaceId: Long, fragmentManager: FragmentManager) {
        viewModel.switchToWorkspace(workspaceId = workspaceId, fragmentManager = fragmentManager)
    }

    fun switchToProject(workspaceId: Long, projectId: Long, fragmentManager: FragmentManager) {
        viewModel.switchToProject(
            workspaceId = workspaceId,
            projectId = projectId,
            fragmentManager = fragmentManager
        )
    }

    private fun updateTextCount() {
        if (viewModel.workspaceListLive.value == null) {
            binding.workspaceNavigationTextWorkspacesCount.text
        }
        else {
            val textCount = "(" + viewModel.workspaceListLive.value!!.size.toString() + ")"
            binding.workspaceNavigationTextWorkspacesCount.text = textCount
        }
    }

    private fun initNavigationDrawer() {
        drawerLayout = binding.workspaceDrawerLayout
        val navigationView = binding.workspaceNavigationView

        val toggle = ActionBarDrawerToggle(activity, drawerLayout,
            R.string.navigation_open, R.string.navigation_close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
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
                binding.workspaceTextHeader.setText(R.string.workspaces_all)
            }
        }

        if (viewModel.headerTextLive.value != null) {
            binding.workspaceTextHeader.text = viewModel.headerTextLive.value
        }
        else if (viewModel.workspaceSectionLive.value != WorkspaceSectionCategory.WORKSPACES_ALL) {
            binding.workspaceTextHeader.setText(R.string.error_load)
        }
    }

    private fun normalizeHeaderTextWidth() {
        val widthAll = viewModel.getDisplayWidth()
        val widthButton = resources.getDimension(R.dimen.header_height)
        val widthIndentInside = resources.getDimension(R.dimen.indent_normal)

        val widthResult = widthAll - widthButton * 4 - widthIndentInside
        binding.workspaceTextHeader.width = widthResult.toInt()
    }

    private fun openNavigationDrawer(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            return false
        }
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }

    private fun closeNavigationDrawer(): Boolean {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            return false
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}