package com.example.data.storage.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "action_workspaces",
    foreignKeys = [
        ForeignKey(
            entity = WorkspaceRoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["workspace_id"]
        ),
    ],
)
class ActionWorkspaceRoomEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(name = "workspace_id") val workspaceId: Long,
)