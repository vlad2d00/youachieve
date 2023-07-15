package com.example.data.storage.db.room.dao

import androidx.room.*
import com.example.data.storage.db.room.entities.ProjectRoomEntity
import com.example.data.storage.db.room.entities.TaskParticipantRoomEntity
import com.example.data.storage.db.room.entities.TaskRoomEntity
import com.example.data.storage.db.room.entities.UserRoomEntity

@Dao
interface TaskRoomDao {

    // INSERT

    @Insert
    fun insertTask(taskRoomEntity: TaskRoomEntity)

    @Insert
    fun insertParticipant(taskParticipantRoomEntity: TaskParticipantRoomEntity)

    
    // UPDATE

    @Update
    fun updateTask(taskRoomEntity: TaskRoomEntity)


    // GET

    @Query("SELECT * FROM tasks " +
            "ORDER BY datetime_begin;")
    fun getTaskListAll(): List<TaskRoomEntity>?

    @Query("SELECT * FROM tasks " +
            "WHERE tasks.id = :taskId " +
            "LIMIT 1;")
    fun getTaskById(taskId: Long): TaskRoomEntity?

    @Query("SELECT tasks.* FROM tasks " +
            "JOIN task_participants  ON task_participants.task_id = tasks.id " +
            "WHERE task_participants.user_id = :userId " +
            "ORDER BY tasks.datetime_begin;")
    fun getTaskListByUserId(userId: Long): List<TaskRoomEntity>?

    @Query("SELECT tasks.* FROM tasks " +
            "JOIN projects  ON projects.id = tasks.project_id " +
            "WHERE projects.id = :projectId " +
            "ORDER BY tasks.datetime_begin;")
    fun getTaskListByProjectId(projectId: Long): List<TaskRoomEntity>?

    @Query("SELECT users.* FROM task_participants " +
            "JOIN users  ON users.id = task_participants.user_id " +
            "WHERE task_participants.task_id = :taskId " +
            "ORDER BY users.first_name, users.last_name;")
    fun getUserListByTaskId(taskId: Long): List<UserRoomEntity>?


    // DELETE

    @Query("DELETE FROM tasks;")
    fun deleteTasksAll()

    @Query("DELETE FROM task_participants;")
    fun deleteParticipantsAll()

    @Query("DELETE FROM tasks " +
            "WHERE id = :taskId;")
    fun deleteTaskById(taskId: Long)

    @Query("DELETE FROM task_participants " +
            "WHERE id = :taskId;")
    fun deleteParticipantsByTaskId(taskId: Long)

    @Query("DELETE FROM task_participants " +
            "WHERE task_id = :taskId AND user_id = :userId;")
    fun deleteParticipantById(taskId: Long, userId: Long)

}