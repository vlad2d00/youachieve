package com.example.youachieve.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.WorkspaceSection
import com.example.youachieve.R
import com.example.youachieve.databinding.ItemWorkspaceSectionBinding
import com.example.youachieve.presentation.adapters.listeners.WorkspaceSectionActionListener
import com.example.youachieve.presentation.utils.Resourses

class WorkspaceSectionAdapter(
    private val workspaceSectionActionListener: WorkspaceSectionActionListener
    ):
    RecyclerView.Adapter<WorkspaceSectionAdapter.WorkspaceSectionViewHolder>(),
    View.OnClickListener {

    var data: List<WorkspaceSection> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    class WorkspaceSectionViewHolder(val binding: ItemWorkspaceSectionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkspaceSectionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWorkspaceSectionBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return WorkspaceSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkspaceSectionViewHolder, position: Int) {
        val item = data[position]
        val context = holder.itemView.context
        holder.itemView.tag = item

        with(holder.binding) {
            val background = if (item.isSelected)
                R.drawable.background_workspace_section_item_selected else
                    R.drawable.background_workspace_section_item
            val contentColor = if (item.isSelected)
                ContextCompat.getColor(context,R.color.text_on_background) else
                    ContextCompat.getColor(context,R.color.text_primary)

            val image = Resourses.getDrawable(resourceName = item.resourceName, context = context)
            val text = Resourses.getString(resourceName = item.resourceName, context = context)

            workspaceSectionItemButton.setBackgroundResource(background)

            workspaceSectionItemImage.setImageDrawable(image)
            workspaceSectionItemImage.setColorFilter(contentColor)

            workspaceSectionItemText.text = text
            workspaceSectionItemText.setTextColor(contentColor)
        }
    }

    override fun onClick(view: View) {
        val item: WorkspaceSection = view.tag as WorkspaceSection
        workspaceSectionActionListener.onSelect(item)
    }

}
