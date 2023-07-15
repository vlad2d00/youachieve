package com.example.data.storage.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = ProjectRoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["project_id"]
        ),
    ],
)
class TaskRoomEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description", defaultValue = "") var description: String,
    @ColumnInfo(name = "is_complete") var isComplete: Boolean,
    @ColumnInfo(name = "datetime_begin", defaultValue = "") var datetimeBegin: String,
    @ColumnInfo(name = "datetime_end", defaultValue = "") var datetimeEnd: String,
    @ColumnInfo(name = "image_cover_name", defaultValue = "") var imageCoverName: String,

    @ColumnInfo(name = "project_id") val projectId: Long,
)