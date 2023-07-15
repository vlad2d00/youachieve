package com.example.domain.models

enum class WorkspaceEntityType(val value: Int) {
    WORKSPACE(1),
    PROJECT(2),
    TASK(3);

    companion object {
        fun toInt(workspaceEntityType: WorkspaceEntityType): Int =
            workspaceEntityType.value

        fun fromInt(value: Int): WorkspaceEntityType? {
            for (item in values()) {
                if (item.value == value) {
                    return item
                }
            }
            return null
        }
    }
}