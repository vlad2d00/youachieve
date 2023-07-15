package com.example.data.storage.db.room.dao

import androidx.room.*
import com.example.data.storage.db.room.entities.*

@Dao
interface WorkspaceRoomDao {

    // INSERT

    @Insert
    fun insertWorkspace(workspaceRoomEntity: WorkspaceRoomEntity)

    @Insert
    fun insertParticipant(workspaceParticipantRoomEntity: WorkspaceParticipantRoomEntity)

    
    // UPDATE

    @Update
    fun updateWorkspace(workspaceRoomEntity: WorkspaceRoomEntity)

    
    // GET

    @Query("SELECT * FROM workspaces " +
            "ORDER BY name;")
    fun getWorkspaceListAll(): List<WorkspaceRoomEntity>?

    @Query("SELECT * FROM workspaces " +
            "WHERE id = :workspaceId " +
            "LIMIT 1;")
    fun getWorkspaceById(workspaceId: Long): WorkspaceRoomEntity?

    @Query("SELECT workspaces.* FROM workspaces " +
            "JOIN workspace_participants ON workspace_participants.workspace_id = workspaces.id " +
            "WHERE workspace_participants.user_id = :userId " +
            "ORDER BY workspaces.name;")
    fun getWorkspaceListByUserId(userId: Long): List<WorkspaceRoomEntity>?

    @Query("SELECT users.* FROM workspace_participants " +
            "JOIN users  ON users.id = workspace_participants.user_id " +
            "WHERE workspace_participants.workspace_id = :workspaceId " +
            "ORDER BY users.first_name, users.last_name;")
    fun getUserListByWorkspaceId(workspaceId: Long): List<UserRoomEntity>?

    
    // DELETE

    @Query("DELETE FROM workspaces;")
    fun deleteWorkspacesAll()

    @Query("DELETE FROM workspace_participants;")
    fun deleteParticipantsAll()

    @Query("DELETE FROM workspaces " +
            "WHERE id = :workspaceId;")
    fun deleteWorkspaceById(workspaceId: Long)

    @Query("DELETE FROM workspace_participants " +
            "WHERE id = :workspaceId;")
    fun deleteParticipantsByWorkspaceId(workspaceId: Long)

    @Query("DELETE FROM workspace_participants " +
            "WHERE workspace_id = :workspaceId AND user_id = :userId;")
    fun deleteParticipantById(workspaceId: Long, userId: Long)

}