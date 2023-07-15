package com.example.data.storage.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "workspace_participants",
    foreignKeys = [
        ForeignKey(
            entity = UserRoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        ),
        ForeignKey(
            entity = WorkspaceRoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["workspace_id"]
        ),
    ],
)
class WorkspaceParticipantRoomEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "workspace_id") val workspaceId: Long,
)