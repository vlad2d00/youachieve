package com.example.data.storage.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "actions",
    foreignKeys = [
        ForeignKey(
            entity = UserRoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        ),
    ],
)
class ActionRoomEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "code") val code: Int,
    @ColumnInfo(name = "args") val args: String,
    @ColumnInfo(name = "datetime") val datetime: String,

    @ColumnInfo(name = "user_id") val userId: Long,
)