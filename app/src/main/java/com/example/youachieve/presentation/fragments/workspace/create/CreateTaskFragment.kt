package com.example.youachieve.presentation.fragments.workspace.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.youachieve.R
import com.example.youachieve.databinding.FragmentCreateTaskBinding
import com.example.youachieve.presentation.fragments.main.MainSectionsFragment
import com.example.youachieve.presentation.fragments.workspace.create.viewmodels.CreateTaskViewModel
import com.example.youachieve.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateTaskFragment : BaseFragment() {

    private val viewModel: CreateTaskViewModel by viewModels()
    private lateinit var binding: FragmentCreateTaskBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateTaskBinding.inflate(layoutInflater)

        initObserves()
        initClickListeners()

        if (savedInstanceState == null) {
            loadWorkspaceSpinner()
        }

        return binding.root
    }

    private fun initObserves() {

        viewModel.isCreatedLive.observe(viewLifecycleOwner) {
            if (viewModel.isCreatedLive.value == true) {
                goToBack()
            }
        }

        viewModel.workspaceListLive.observe(viewLifecycleOwner) {
            val selectedItemId = viewModel.getWorkspaceItemIdSelectedDefault()
            updateWorkspaceSpinner(selectedItemId = selectedItemId)
        }

        viewModel.projectListLive.observe(viewLifecycleOwner) {
            val selectedItemId = viewModel.getProjectItemIdSelectedDefault()
            updateProjectSpinner(selectedItemId = selectedItemId)
        }

        viewModel.taskNameErrorResIdLive.observe(viewLifecycleOwner) {
            updateTaskNameError()
        }

        viewModel.workspaceSelectedErrorResIdLive.observe(viewLifecycleOwner) {
            updateWorkspaceSelectedError()
        }

        viewModel.projectSelectedErrorResIdLive.observe(viewLifecycleOwner) {
            updateProjectSelectedError()
        }

    }

    private fun initClickListeners() {

        binding.createTaskButtonCancel.setOnClickListener {
            goToBack()
        }

        binding.createTaskButtonOk.setOnClickListener {
            viewModel.createTask(
                workspaceItemId = binding.createTaskSpinnerWorkspace.selectedItemId.toInt(),
                projectItemId = binding.createTaskSpinnerProject.selectedItemId.toInt(),
                name = binding.createTaskEditTextName.text.toString(),
                description = binding.createTaskEditTextDescription.text.toString(),
            )
        }

        binding.createTaskButtonDatetimeBegin.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Установить дату начала задачи пока невозможно", Toast.LENGTH_SHORT).show()
        }

        binding.createTaskButtonDatetimeEnd.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Установить срок выполнения задачи пока невозможно", Toast.LENGTH_SHORT).show()
        }

        binding.createTaskButtonAddCover.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Добавить обложку к задаче пока невозможно", Toast.LENGTH_SHORT).show()
        }

        binding.createTaskButtonAddUser.setOnClickListener {
            // TODO Реализовать позднее
            Toast.makeText(binding.root.context,
                "Добавить участников для задачи пока невозможно", Toast.LENGTH_SHORT).show()
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
        binding.createTaskSpinnerWorkspace.adapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_item,
            arrayOf(getString(R.string.loading))
        )

        viewModel.loadWorkspaceList()
    }

    private fun loadProjectSpinner() {
        val arrayAdapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_item,
            arrayOf(getString(R.string.loading))
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.createTaskSpinnerProject.adapter = arrayAdapter

        viewModel.loadProjectList(workspaceItemId = binding.createTaskSpinnerWorkspace.selectedItemId.toInt())
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
            else {
                result.toList()
            }
        }

        val arrayAdapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_item,
            items
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.createTaskSpinnerWorkspace.onItemSelectedListener =
            object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                loadProjectSpinner()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        binding.createTaskSpinnerWorkspace.adapter = arrayAdapter

        binding.createTaskSpinnerWorkspace.setSelection(selectedItemId)
    }

    private fun updateProjectSpinner(selectedItemId: Int = 0) {
        val items = if (viewModel.projectListLive.value == null) {
            listOf(getString(R.string.error_load))
        }
        else {
            val result = arrayListOf<String>()
            for (workspace in viewModel.projectListLive.value!!) {
                result.add(workspace.name)
            }

            if (result.isEmpty())
                listOf(getString(R.string.empty))
            else {
                result.toList()
            }
        }

        val arrayAdapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_item,
            items
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.createTaskSpinnerProject.adapter = arrayAdapter

        binding.createTaskSpinnerProject.setSelection(selectedItemId)
    }

    private fun updateTaskNameError() {
        if (viewModel.taskNameErrorResIdLive.value == null) {
            binding.createTaskTextNameError.isVisible = false
        }
        else {
            binding.createTaskTextNameError.isVisible = true
            binding.createTaskTextNameError.setText(viewModel.taskNameErrorResIdLive.value!!)
        }
    }

    private fun updateWorkspaceSelectedError() {
        if (viewModel.workspaceSelectedErrorResIdLive.value == null) {
            binding.createTaskSpinnerWorkspaceError.isVisible = false
        }
        else {
            binding.createTaskSpinnerWorkspaceError.isVisible = true
            binding.createTaskSpinnerWorkspaceError.setText(viewModel.workspaceSelectedErrorResIdLive.value!!)
        }
    }

    private fun updateProjectSelectedError() {
        if (viewModel.projectSelectedErrorResIdLive.value == null) {
            binding.createTaskSpinnerProjectError.isVisible = false
        }
        else {
            binding.createTaskSpinnerProjectError.isVisible = true
            binding.createTaskSpinnerProjectError.setText(viewModel.projectSelectedErrorResIdLive.value!!)
        }
    }

}