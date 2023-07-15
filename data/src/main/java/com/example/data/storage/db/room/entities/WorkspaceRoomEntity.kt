package com.example.data.storage.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "workspaces"
)
class WorkspaceRoomEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "short_name") var shortName: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description", defaultValue = "") var description: String,
    @ColumnInfo(name = "is_private") var isPrivate: Boolean,
    @ColumnInfo(name = "image_avatar_name", defaultValue = "") var imageAvatarName: String,
    @ColumnInfo(name = "image_cover_name", defaultValue = "") var imageCoverName: String,
)