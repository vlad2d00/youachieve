package com.example.data.storage.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.storage.db.room.dao.*
import com.example.data.storage.db.room.entities.*

@Database(
    version = 3,
    entities = [
        UserRoomEntity::class,

        ActionProjectRoomEntity::class,
        ActionRoomEntity::class,
        ActionWorkspaceRoomEntity::class,

        ProjectRoomEntity::class,
        ProjectParticipantRoomEntity::class,

        TaskRoomEntity::class,
        TaskParticipantRoomEntity::class,

        WorkspaceRoomEntity::class,
        WorkspaceParticipantRoomEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getActionDao(): ActionRoomDao

    abstract fun getUserDao(): UserRoomDao

    abstract fun getProjectDao(): ProjectRoomDao
    abstract fun getTaskDao(): TaskRoomDao
    abstract fun getWorkspaceDao(): WorkspaceRoomDao

}

