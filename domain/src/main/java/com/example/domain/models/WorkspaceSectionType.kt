package com.example.domain.models

enum class WorkspaceSectionType(val value: Int) {
    WORKSPACES(1),
    PROJECTS(2),
    TASKS(3),
    USERS(4),
    ACTIONS(5);

    companion object {
        fun toInt(workspaceSectionType: WorkspaceSectionType) = workspaceSectionType.value
    }

}