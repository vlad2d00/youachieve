package com.example.youachieve.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.workspace.TaskGroup
import com.example.youachieve.R
import com.example.youachieve.databinding.ItemTaskGroupBinding
import com.example.youachieve.presentation.adapters.decorators.GridSpacingItemDecoration
import com.example.youachieve.presentation.adapters.listeners.TaskActionListener
import com.example.youachieve.presentation.adapters.listeners.TaskGroupActionListener

class TaskGroupAdapter(
    private val taskGroupActionListener: TaskGroupActionListener,
    private val taskActionListener: TaskActionListener
    ):
    RecyclerView.Adapter<TaskGroupAdapter.TaskGroupViewHolder>(),
    View.OnClickListener {

    var data: ArrayList<TaskGroup> = arrayListOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    private lateinit var taskAdapter: TaskAdapter


    class TaskGroupViewHolder(val binding: ItemTaskGroupBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskGroupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskGroupBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.taskGroupItemImageSpoiler.setOnClickListener(this)

        return TaskGroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskGroupViewHolder, position: Int) {
        val item = data[position]
        val context = holder.itemView.context

        holder.itemView.tag = item
        holder.binding.taskGroupItemImageSpoiler.tag = item

        with(holder.binding) {
            taskGroupItemTextName.text = item.name
            taskGroupItemTextCountItems.text = item.taskList.size.toString()

            if (item.isOpen) {
                taskGroupItemImageSpoiler.rotation = 0F
            }
            else {
                taskGroupItemImageSpoiler.rotation = -90F

                taskAdapter = TaskAdapter(taskActionListener)
                val layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL, false)
                val itemDecoration = GridSpacingItemDecoration(
                    spacing = context.resources.getDimension(R.dimen.indent_normal).toInt(),
                    orientation = LinearLayoutManager.VERTICAL,
                    includeEdge = false
                )

                taskAdapter.data = item.taskList

                taskGroupItemRecyclerViewTasks.layoutManager = layoutManager
                taskGroupItemRecyclerViewTasks.invalidateItemDecorations()
                taskGroupItemRecyclerViewTasks.addItemDecoration(itemDecoration)
                taskGroupItemRecyclerViewTasks.adapter = taskAdapter
            }
        }
    }

    override fun onClick(view: View) {
        val item = view.tag as TaskGroup

        when (view.id) {
            R.id.taskGroupItemImageSpoiler -> taskGroupActionListener.onSpoiler(item, !item.isOpen)
        }
    }

    fun getPositionByItem(item: TaskGroup): Int {
        for (i in data.indices) {
            if (data[i] == item) {
                return i
            }
        }
        return -1
    }

}