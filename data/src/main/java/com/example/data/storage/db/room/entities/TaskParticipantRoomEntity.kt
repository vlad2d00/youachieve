package com.example.data.storage.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "task_participants",
    foreignKeys = [
        ForeignKey(
            entity = UserRoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        ),
        ForeignKey(
            entity = TaskRoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["task_id"]
        ),
    ],
)
class TaskParticipantRoomEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "task_id") val taskId: Long,
)