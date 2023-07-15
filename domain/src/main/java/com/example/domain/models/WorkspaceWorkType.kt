package com.example.domain.models

enum class WorkspaceWorkType(val value: Int) {
    CREATE(1),
    EDIT(2);

    companion object {
        fun toInt(workspaceWorkType: WorkspaceWorkType): Int =
            workspaceWorkType.value

        fun fromInt(value: Int): WorkspaceWorkType? {
            for (item in values()) {
                if (item.value == value) {
                    return item
                }
            }
            return null
        }
    }
}