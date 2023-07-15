package com.example.youachieve.presentation.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import com.example.domain.models.ResourceName
import com.example.youachieve.R

class Resourses {

    companion object {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun getDrawable(resourceName: ResourceName, context: Context): Drawable? {
            return when (resourceName) {
                ResourceName.WORKSPACE_SECTION_WORKSPACE -> context.getDrawable(R.drawable.workspace)
                ResourceName.WORKSPACE_SECTION_PROJECTS -> context.getDrawable(R.drawable.project)
                ResourceName.WORKSPACE_SECTION_TASKS -> context.getDrawable(R.drawable.task)
                ResourceName.WORKSPACE_SECTION_USERS -> context.getDrawable(R.drawable.users)
                ResourceName.WORKSPACE_SECTION_ACTIONS -> context.getDrawable(R.drawable.clock)
            }
        }

        fun getString(resourceName: ResourceName, context: Context): String {
            return when (resourceName) {
                ResourceName.WORKSPACE_SECTION_WORKSPACE -> context.getString(R.string.workspace_section_workspaces)
                ResourceName.WORKSPACE_SECTION_PROJECTS -> context.getString(R.string.workspace_section_projects)
                ResourceName.WORKSPACE_SECTION_TASKS -> context.getString(R.string.workspace_section_tasks)
                ResourceName.WORKSPACE_SECTION_USERS -> context.getString(R.string.workspace_section_users)
                ResourceName.WORKSPACE_SECTION_ACTIONS -> context.getString(R.string.workspace_section_actions)
            }
        }

    }
}