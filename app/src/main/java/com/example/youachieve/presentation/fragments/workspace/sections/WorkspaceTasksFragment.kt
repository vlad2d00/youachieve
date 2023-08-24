package com.example.youachieve.presentation.fragments.workspace.sections

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.workspace.Task
import com.example.domain.models.workspace.TaskGroup
import com.example.domain.utils.DatetimeService
import com.example.youachieve.R
import com.example.youachieve.databinding.FragmentWorkspaceTasksBinding
import com.example.youachieve.presentation.adapters.TaskAdapter
import com.example.youachieve.presentation.adapters.TaskGroupAdapter
import com.example.youachieve.presentation.adapters.decorators.GridSpacingItemDecoration
import com.example.youachieve.presentation.adapters.listeners.TaskActionListener
import com.example.youachieve.presentation.adapters.listeners.TaskGroupActionListener
import com.example.youachieve.presentation.fragments.workspace.sections.viewmodels.WorkspaceTasksViewModel
import com.example.youachieve.presentation.base.BaseFragment
import com.example.youachieve.presentation.utils.MyResources
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkspaceTasksFragment : BaseFragment() {

    private val viewModel: WorkspaceTasksViewModel by viewModels()
    private lateinit var binding: FragmentWorkspaceTasksBinding
    private lateinit var taskGroupAdapter: TaskGroupAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkspaceTasksBinding.inflate(layoutInflater)

        initObserves()
        initClickListeners()
        initTaskGroupAdapter()

        if (savedInstanceState == null) {
            viewModel.loadTaskList()
        }

        return binding.root
    }

    private fun initObserves() {

        viewModel.taskGroupListLive.observe(viewLifecycleOwner) {
            updateMessageText()
            updateTaskList()
        }

    }

    private fun initClickListeners() {

    }

    private fun initTaskGroupAdapter() {
        taskGroupAdapter = TaskGroupAdapter(
            taskGroupActionListener = object : TaskGroupActionListener {

            override fun onSpoiler(taskGroup: TaskGroup, isOpen: Boolean) {
                val position = taskGroupAdapter.getPositionByItem(taskGroup)
                taskGroupAdapter.data[position].isOpen = isOpen
                taskGroupAdapter.notifyItemChanged(position)
            }

        },
        taskActionListener = object : TaskActionListener {

            override fun onSelect(task: Task) {
                // TODO Реализовать позднее
                Toast.makeText(context,
                    "Перейти в настройки задачи пока невозможно", Toast.LENGTH_SHORT).show()
            }

            override fun onUpdateStatus(task: Task, isComplete: Boolean, taskAdapter: TaskAdapter) {
                viewModel.updateStatusTask(taskId = task.id, isComplete = isComplete)
                val position = taskAdapter.getPositionByItemId(task.id)
                taskAdapter.data[position].isComplete = isComplete
                taskAdapter.notifyItemChanged(position)
            }

        })

        val layoutManager = LinearLayoutManager(
            binding.root.context, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = GridSpacingItemDecoration(
            spacing = resources.getDimension(R.dimen.indent_normal).toInt(),
            orientation = LinearLayoutManager.VERTICAL,
            includeEdge = true
        )

        binding.workspaceTasksRecyclerView.layoutManager = layoutManager
        binding.workspaceTasksRecyclerView.addItemDecoration(itemDecoration)
        binding.workspaceTasksRecyclerView.adapter = taskGroupAdapter
    }

    private fun updateMessageText() {
        if (viewModel.taskGroupListLive.value != null) {
            if (viewModel.taskGroupListLive.value!!.isEmpty()) {
                binding.workspaceTasksTextStatus.isVisible = true
                binding.workspaceTasksTextStatus.setText(R.string.task_list_is_empty)
            }
            else {
                binding.workspaceTasksTextStatus.isVisible = false
                binding.workspaceTasksTextStatus.setText(R.string.loading)
            }
        }
        else {
            binding.workspaceTasksTextStatus.isVisible = true
            binding.workspaceTasksTextStatus.setText(R.string.error_load)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateTaskList() {
        if (viewModel.taskGroupListLive.value == null) {
            return
        }

        for (taskGroup in viewModel.taskGroupListLive.value!!) {
            val nameResult = if (taskGroup.name == "") {
                getString(R.string.no_datetime_limit)
            }
            else {
                val date = DatetimeService.toDate(taskGroup.name)
                MyResources.toStringFull(date = date, context = binding.root.context)
            }

            taskGroupAdapter.data.add(
                TaskGroup(
                    name = nameResult,
                    taskList = taskGroup.taskList,
                    isOpen = taskGroup.isOpen
                ))
        }
        taskGroupAdapter.notifyDataSetChanged()
    }

}