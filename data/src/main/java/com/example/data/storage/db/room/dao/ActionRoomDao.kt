package com.example.data.storage.db.room.dao

import androidx.room.*
import com.example.data.storage.db.room.entities.ActionProjectRoomEntity
import com.example.data.storage.db.room.entities.ActionRoomEntity
import com.example.data.storage.db.room.entities.ActionWorkspaceRoomEntity

@Dao
interface ActionRoomDao {

    // INSERT

    @Insert
    fun insertAction(actionRoomEntity: ActionRoomEntity)

    @Insert
    fun insertAction(actionProjectRoomEntity: ActionProjectRoomEntity)

    @Insert
    fun insertAction(actionWorkspaceRoomEntity: ActionWorkspaceRoomEntity)


    // GET

    @Query("SELECT * FROM actions;")
    fun getActionListAll(): List<ActionRoomEntity>?

    @Query("SELECT * FROM action_projects;")
    fun getActionListForProjects(): List<ActionProjectRoomEntity>?

    @Query("SELECT * FROM action_workspaces;")
    fun getActionListForWorkspaces(): List<ActionWorkspaceRoomEntity>?


    // DELETE

    @Query("DELETE FROM actions;")
    fun deleteActionsAll()

    @Query("DELETE FROM action_projects;")
    fun deleteActionsForProjects()

    @Query("DELETE FROM action_workspaces;")
    fun deleteActionsForWorkspaces()

}