package com.example.youachieve.presentation.fragments.workspace.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.youachieve.R
import com.example.youachieve.databinding.FragmentCreateProjectBinding
import com.example.youachieve.presentation.fragments.main.MainSectionsFragment
import com.example.youachieve.presentation.fragments.workspace.create.viewmodels.CreateProjectViewModel
import com.example.youachieve.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateProjectFragment : BaseFragment() {

    private val viewModel: CreateProjectViewModel by viewModels()
    private lateinit var binding: FragmentCreateProjectBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateProjectBinding.inflate(layoutInflater)

        initObserves()
        initClickListeners()

        if (savedInstanceState == null) {
            loadWorkspaceSpinner()
        }

        return binding.root
    }

    private fun initObserves() {

        viewModel.projectNameErrorResIdLive.observe(viewLifecycleOwner) {
            updateProjectNameError()
        }

        viewModel.workspaceSelectedErrorResIdLive.observe(viewLifecycleOwner) {
            updateWorkspaceSelectedError()
        }

        viewModel.isCreatedLive.observe(viewLifecycleOwner) {
            if (viewModel.isCreatedLive.value == true) {
                goToBack()
            }
        }

        viewModel.workspaceListLive.observe(viewLifecycleOwner) {
            val selectedItemId = viewModel.getWorkspaceItemIdSelectedDefault()
            updateWorkspaceSpinner(selectedItemId = selectedItemId)
        }

    }

    private fun initClickListeners() {

        binding.createProjectButtonCancel.setOnClickListener {
            goToBack()
        }

        binding.createProjectButtonOk.setOnClickListener {
            viewModel.createProject(
                workspaceItemId = binding.createProjectSpinnerWorkspace.selectedItemId.toInt(),
                name = binding.createProjectEditTextName.text.toString(),
                description = binding.createProjectEditTextDescription.text.toString(),
                isPrivate = binding.createProjectSwitchIsPrivate.isChecked
            )
        }

        binding.createProjectButtonAddAvatar.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Добавить аватарку к проекту пока невозможно", Toast.LENGTH_SHORT).show()
        }

        binding.createProjectButtonAddCover.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Добавить обложку к проекту пока невозможно", Toast.LENGTH_SHORT).show()
        }

    }

    private fun goToBack() {
        parentFragmentManager.commit {
            setCustomAnimations(
                R.anim.appearance,
                R.anim.slide_to_right,
            )
            replace(R.id.mainFragment, MainSectionsFragment())
        }
    }

    private fun loadWorkspaceSpinner() {
        val arrayAdapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_item,
            arrayOf(getString(R.string.loading))
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.createProjectSpinnerWorkspace.adapter = arrayAdapter

        viewModel.loadWorkspaceList()
    }

    private fun updateWorkspaceSpinner(selectedItemId: Int = 0) {
        val items = if (viewModel.workspaceListLive.value == null) {
            listOf(getString(R.string.error_load))
        }
        else {
            val result = arrayListOf<String>()
            for (workspace in viewModel.workspaceListLive.value!!) {
                result.add(workspace.name)
            }

            if (result.isEmpty())
                listOf(getString(R.string.empty))
            else
                result.toList()
        }

        val arrayAdapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_item,
            items
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.createProjectSpinnerWorkspace.adapter = arrayAdapter

        binding.createProjectSpinnerWorkspace.setSelection(selectedItemId)
    }

    private fun updateProjectNameError() {
        if (viewModel.projectNameErrorResIdLive.value == null) {
            binding.createProjectTextNameError.isVisible = false
        }
        else {
            binding.createProjectTextNameError.isVisible = true
            binding.createProjectTextNameError.setText(viewModel.projectNameErrorResIdLive.value!!)
        }
    }

    private fun updateWorkspaceSelectedError() {
        if (viewModel.workspaceSelectedErrorResIdLive.value == null) {
            binding.createProjectSpinnerWorkspaceError.isVisible = false
        }
        else {
            binding.createProjectSpinnerWorkspaceError.isVisible = true
            binding.createProjectSpinnerWorkspaceError.setText(viewModel.workspaceSelectedErrorResIdLive.value!!)
        }
    }

}