package com.example.youachieve.presentation.ui.fragments.workspace.create

import android.os.Bundle
import androidx.fragment.app.Fragment
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
import com.example.youachieve.presentation.ui.fragments.main.MainSectionsFragment
import com.example.youachieve.presentation.ui.fragments.workspace.create.viewmodels.CreateProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateProjectFragment : Fragment() {

    private val viewModel: CreateProjectViewModel by viewModels()
    private lateinit var binding: FragmentCreateProjectBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateProjectBinding.inflate(layoutInflater)

        viewModel.isCreatedLive.observe(viewLifecycleOwner) {
            if (viewModel.isCreatedLive.value == true) {
                goToBack()
            }
        }

        viewModel.workspaceListLive.observe(viewLifecycleOwner) {
            updateWorkspaceSpinner()
        }

        binding.createProjectButtonCancel.setOnClickListener {
            goToBack()
        }

        binding.createProjectButtonOk.setOnClickListener {
            saveEnteredData()
            viewModel.createProject()
        }

        viewModel.projectNameErrorResIdLive.observe(viewLifecycleOwner) {
            updateProjectNameError()
        }

        viewModel.workspaceSelectedErrorResIdLive.observe(viewLifecycleOwner) {
            updateWorkspaceSelectedError()
        }

        binding.createProjectButtonAddAvatar.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Добавить аватарку к проекту пока нельзя", Toast.LENGTH_SHORT).show()
        }

        binding.createProjectButtonAddCover.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Добавить обложку к проекту пока нельзя", Toast.LENGTH_SHORT).show()
        }

        loadWorkspaceSpinner()

        loadEnteredData()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        saveEnteredData()
    }

    private fun goToBack() {
        parentFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_to_right,
                R.anim.appearance_small
            )
            replace(R.id.mainFragment, MainSectionsFragment())
        }
    }

    private fun saveEnteredData() {
        viewModel.workspaceSelectedItemId = binding.createProjectSpinnerWorkspace.selectedItemId

        viewModel.projectName = binding.createProjectEditTextName.text.toString()
        viewModel.projectDescription = binding.createProjectEditTextDescription.text.toString()
        viewModel.projectIsPrivate = binding.createProjectSwitchIsPrivate.isChecked
    }

    private fun loadEnteredData() {
        binding.createProjectSpinnerWorkspace.setSelection(viewModel.workspaceSelectedItemId.toInt())

        binding.createProjectEditTextName.setText(viewModel.projectName)
        binding.createProjectEditTextDescription.setText(viewModel.projectDescription)
        binding.createProjectSwitchIsPrivate.isChecked = viewModel.projectIsPrivate
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

    private fun updateWorkspaceSpinner() {
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