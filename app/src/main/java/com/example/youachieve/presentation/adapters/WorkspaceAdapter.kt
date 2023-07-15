package com.example.youachieve.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Workspace
import com.example.youachieve.R
import com.example.youachieve.databinding.ItemProjectBinding
import com.example.youachieve.presentation.adapters.listeners.WorkspaceActionListener

class WorkspaceAdapter(
    private val workspaceActionListener: WorkspaceActionListener
    ):
    RecyclerView.Adapter<WorkspaceAdapter.WorkspaceViewHolder>(),
    View.OnClickListener {

    var data: List<Workspace> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    class WorkspaceViewHolder(val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkspaceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProjectBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.projectItemButtonOther.setOnClickListener(this)

        return WorkspaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkspaceViewHolder, position: Int) {
        val item = data[position]
        val context = holder.itemView.context

        holder.itemView.tag = item
        holder.binding.projectItemButtonOther.tag = item

        with(holder.binding) {
            projectItemImageAvatar.setImageResource(R.drawable.workspace)
            projectItemImageCover.setImageResource(R.drawable.image_cover)
            projectItemText.text = item.name
        }
    }

    override fun onClick(view: View) {
        val item: Workspace = view.tag as Workspace

        when (view.id) {
            R.id.projectItemButtonOther -> workspaceActionListener.onSettings(item)
            else -> workspaceActionListener.onSelect(item)
        }
    }

}