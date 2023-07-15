package com.example.data.storage.db.room.dao

import androidx.room.*
import com.example.data.storage.db.room.entities.*

@Dao
interface ProjectRoomDao {

    // INSERT

    @Insert
    fun insertProject(projectRoomEntity: ProjectRoomEntity)

    @Insert
    fun insertParticipant(projectParticipantRoomEntity: ProjectParticipantRoomEntity)

    
    // UPDATE

    @Update
    fun updateProject(projectRoomEntity: ProjectRoomEntity)

    
    // GET
    
    @Query("SELECT * FROM projects " +
            "ORDER BY name;")
    fun getProjectListAll(): List<ProjectRoomEntity>?

    @Query("SELECT * FROM projects " +
            "WHERE id = :projectId " +
            "LIMIT 1;")
    fun getProjectById(projectId: Long): ProjectRoomEntity?

    @Query("SELECT projects.* FROM projects " +
            "JOIN project_participants  ON project_participants.project_id = projects.id " +
            "WHERE project_participants.user_id = :userId " +
            "ORDER BY projects.name;")
    fun getProjectListByUserId(userId: Long): List<ProjectRoomEntity>?

    @Query("SELECT projects.* FROM projects " +
            "JOIN workspaces  ON workspaces.id = projects.workspace_id " +
            "WHERE workspaces.id = :workspaceId " +
            "ORDER BY projects.name;")
    fun getProjectListByWorkspaceId(workspaceId: Long): List<ProjectRoomEntity>?

    @Query("SELECT users.* FROM project_participants " +
            "JOIN users  ON users.id = project_participants.user_id " +
            "WHERE project_participants.project_id = :projectId " +
            "ORDER BY users.first_name, users.last_name;")
    fun getUserListByProjectId(projectId: Long): List<UserRoomEntity>?

    
    // DELETE
    
    @Query("DELETE FROM projects;")
    fun deleteProjectsAll()

    @Query("DELETE FROM project_participants;")
    fun deleteParticipantsAll()

    @Query("DELETE FROM projects " +
            "WHERE id = :projectId;")
    fun deleteProjectById(projectId: Long)

    @Query("DELETE FROM project_participants " +
            "WHERE id = :projectId;")
    fun deleteParticipantsByProjectId(projectId: Long)

    @Query("DELETE FROM project_participants " +
            "WHERE project_id = :projectId AND user_id = :userId;")
    fun deleteParticipantById(projectId: Long, userId: Long)

}