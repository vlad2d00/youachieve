package com.example.youachieve.presentation.adapters

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.workspace.Task
import com.example.youachieve.R
import com.example.youachieve.databinding.ItemTaskBinding
import com.example.youachieve.presentation.adapters.listeners.TaskActionListener
import com.example.youachieve.presentation.utils.MyResources
import java.time.LocalDateTime

class TaskAdapter(
    private val taskActionListener: TaskActionListener
    ):
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(),
    View.OnClickListener {

    var data: ArrayList<Task> = arrayListOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.taskItemCheckbox.setOnClickListener(this)

        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = data[position]
        val context = holder.itemView.context

        holder.itemView.tag = item
        holder.binding.taskItemCheckbox.tag = item

        with(holder.binding) {
            taskItemImageCover.setImageResource(R.drawable.image_cover)
            taskItemTextName.text = item.name
            taskItemTextProjectName.text = item.projectName

            if (item.description == "") {
                taskItemTextDescription.visibility = View.GONE
            }
            else {
                taskItemTextDescription.visibility = View.VISIBLE
                taskItemTextDescription.text = item.description
            }

            // Если задача выполнена
            if (item.isComplete) {
                taskItemCheckbox.setBackgroundResource(R.drawable.background_checkbox_checked)
                taskItemImageChecked.visibility = View.VISIBLE
                taskItemTextName.setTextColor(getColor(context, R.color.text_secondary))
                taskItemTextName.paintFlags = taskItemTextName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else {
                taskItemCheckbox.setBackgroundResource(R.drawable.background_checkbox)
                taskItemImageChecked.visibility = View.GONE
                taskItemTextName.setTextColor(getColor(context, R.color.text_primary))
                taskItemTextName.paintFlags = taskItemTextName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }

            var datetimeColor = R.color.text_secondary
            if (item.datetimeEnd != null) {
                datetimeColor = if (item.datetimeEnd!! >= LocalDateTime.now())
                    R.color.datetime_overdue else R.color.datetime_far
            }
            taskItemTextDatetime.setTextColor(context.resources.getColor(datetimeColor))

            // Без срока
            if (item.datetimeBegin == null && item.datetimeEnd == null) {
                taskItemTextDatetime.setText(R.string.no_datetime_limit)
            }
            // Только конечный срок
            else if (item.datetimeBegin == null && item.datetimeEnd != null) {
                val text = context.getString(R.string.word_before) + " " +
                        MyResources.toStringFull(
                            datetime = item.datetimeEnd!!,
                            context = context
                        )
                taskItemTextDatetime.text = text
            }
            // Только начальный срок
            else if (item.datetimeBegin != null && item.datetimeEnd == null) {
                val text = context.getString(R.string.word_from) + " " +
                        MyResources.toStringFull(
                            datetime = item.datetimeBegin!!,
                            context = context
                        )
                taskItemTextDatetime.text = text
            }
            // Начальный и конечный срок
            else {
                taskItemTextDatetime.text = MyResources.toStringFull(
                    datetimeBegin = item.datetimeBegin!!,
                    datetimeEnd = item.datetimeEnd!!,
                    context = context
                )
            }
        }
    }

    override fun onClick(view: View) {
        val item = view.tag as Task

        when (view.id) {
            R.id.taskItemCheckbox ->
                taskActionListener.onUpdateStatus(item, !item.isComplete, this)

            else -> taskActionListener.onSelect(item)
        }
    }

    fun getPositionByItemId(itemId: Long): Int {
        for (i in data.indices) {
            if (data[i].id == itemId) {
                return i
            }
        }
        return -1
    }

}