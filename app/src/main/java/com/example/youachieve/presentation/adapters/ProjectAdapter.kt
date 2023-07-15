package com.example.youachieve.presentation.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Project
import com.example.youachieve.R
import com.example.youachieve.databinding.ItemProjectBinding
import com.example.youachieve.presentation.adapters.listeners.ProjectActionListener

class ProjectAdapter(
    private val projectActionListener: ProjectActionListener
    ):
    RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>(),
    View.OnClickListener {

    var data: List<Project> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    class ProjectViewHolder(val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProjectBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.projectItemButtonOther.setOnClickListener(this)

        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val item = data[position]
        val context = holder.itemView.context

        holder.itemView.tag = item
        holder.binding.projectItemButtonOther.tag = item

        with(holder.binding) {
            projectItemImageAvatar.setImageResource(R.drawable.project)
            projectItemImageCover.setImageResource(R.drawable.image_cover)
            projectItemText.text = item.name
            Log.d("Test", "item.name \"${item.name}\"")
        }
    }

    override fun onClick(view: View) {
        val item: Project = view.tag as Project

        when (view.id) {
            R.id.projectItemButtonOther -> projectActionListener.onSettings(item)
            else -> projectActionListener.onSelect(item)
        }
    }

}