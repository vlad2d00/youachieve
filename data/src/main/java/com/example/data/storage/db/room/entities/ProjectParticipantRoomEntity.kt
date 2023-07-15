package com.example.data.storage.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "project_participants",
    foreignKeys = [
        ForeignKey(
            entity = UserRoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        ),
        ForeignKey(
            entity = ProjectRoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["project_id"]
        ),
    ],
)
class ProjectParticipantRoomEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "project_id") val projectId: Long,
)