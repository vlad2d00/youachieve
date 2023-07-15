package com.example.data.storage.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "users"
)
data class UserRoomEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "short_name") var shortName: String,
    @ColumnInfo(name = "first_name") var firstName: String,
    @ColumnInfo(name = "last_name", defaultValue = "") var lastName: String,
    @ColumnInfo(name = "description", defaultValue = "") var description: String,
    @ColumnInfo(name = "datetime_last_activity") var datetimeLastActivity: String,
    @ColumnInfo(name = "image_avatar_name", defaultValue = "") var imageAvatarName: String,
)