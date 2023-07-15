package com.example.data.storage.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "projects",
    foreignKeys = [
        ForeignKey(
            entity = WorkspaceRoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["workspace_id"]
        ),
    ],
)
class ProjectRoomEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description", defaultValue = "") var description: String,
    @ColumnInfo(name = "is_private") var isPrivate: Boolean,
    @ColumnInfo(name = "image_avatar_name", defaultValue = "") var imageAvatarName: String,
    @ColumnInfo(name = "image_cover_name", defaultValue = "") var imageCoverName: String,

    @ColumnInfo(name = "workspace_id") val workspaceId: Long,
)