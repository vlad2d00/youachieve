package com.example.data.storage.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "action_projects",
    foreignKeys = [
        ForeignKey(
            entity = ProjectRoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["project_id"]
        ),
    ],
)
class ActionProjectRoomEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(name = "project_id") val projectId: Long,
)