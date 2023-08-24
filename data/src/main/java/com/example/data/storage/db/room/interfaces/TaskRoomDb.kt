package com.example.data.storage.db.room.interfaces

import android.content.Context
import com.example.data.storage.db.room.DatabaseRoom
import com.example.data.storage.db.room.entities.ProjectRoomEntity
import com.example.data.storage.db.room.entities.TaskRoomEntity
import com.example.data.storage.db.room.mapper.TaskRoomMapper
import com.example.data.storage.interfaces.TaskStorage
import com.example.domain.models.base.Status
import com.example.domain.models.workspace.Task
import com.example.domain.utils.DatetimeService
import java.time.LocalDateTime
import kotlin.math.min

class TaskRoomDb(context: Context): TaskStorage {

    companion object {
        private const val COUNT_DEFAULT = 10
    }

    private val taskDao = DatabaseRoom.get(context).getTaskDao()
    private val projectDao = DatabaseRoom.get(context).getProjectDao()


    override fun createTask(
        projectId: Long,
        name: String,
        description: String?,
        imageCoverName: String?,
        datetimeBegin: LocalDateTime?,
        datetimeEnd: LocalDateTime?,
        status: Status?
    ) {
        taskDao.insertTask(
            TaskRoomEntity(
                id = 0,
                name = name,
                description = description ?: "",
                imageCoverName = imageCoverName ?: "",
                datetimeBegin = if (datetimeBegin == null) "" else DatetimeService.toStringFull(datetimeBegin),
                datetimeEnd = if (datetimeEnd == null) "" else DatetimeService.toStringFull(datetimeEnd),
                isComplete = false,
                projectId = projectId,
            )
        )
    }

    override fun updateTask(
        taskId: Long,
        name: String?,
        description: String?,
        imageCoverName: String?,
        datetimeBegin: LocalDateTime?,
        datetimeEnd: LocalDateTime?,
        isComplete: Boolean?,
        status: Status?
    ) {
        val task = taskDao.getTaskById(taskId) ?: return

        if (name != null)
            task.name = name

        if (description != null)
            task.description = description

        if (imageCoverName != null)
            task.imageCoverName = imageCoverName

        if (datetimeBegin != null)
            task.datetimeBegin = DatetimeService.toStringFull(datetimeBegin)

        if (datetimeEnd != null)
            task.datetimeEnd = DatetimeService.toStringFull(datetimeEnd)

        if (isComplete != null)
            task.isComplete = isComplete

        taskDao.updateTask(task)
    }

    override fun getTask(taskId: Long, status: Status?): Task? {
        val task = taskDao.getTaskById(taskId) ?: return null
        val project = projectDao.getProjectById(task.projectId)

        return TaskRoomMapper.toDomain(taskRoomEntity = task, projectName = project!!.name)
    }

    override fun getTaskList(
        workspaceId: Long,
        projectId: Long,
        format: String?,
        datetimeBegin: LocalDateTime?,
        datetimeEnd: LocalDateTime?,
        isComplete: Boolean?,
        count: Int?,
        offset: Int,
        status: Status?
    ): List<Task>? {
        val taskList = if (workspaceId == 0L) {
            if (projectId == 0L) {
                taskDao.getTaskListAll() ?: return null
                // TODO Когда будут реализованы операции с пользователем, изменить
//            projectDao.getProjectListByUserId(workspaceId) ?: return null
            } else {
                taskDao.getTaskListByProjectId(projectId) ?: return null
            }
        } else {
            taskDao.getTaskListByWorkspaceId(workspaceId) ?: return null
        }

        val result = arrayListOf<Task>()
        val countResult = count ?: COUNT_DEFAULT
        val formatResult = format?.trim() ?: ""
        val projectMap = mutableMapOf<Long, ProjectRoomEntity>()

        for (i in offset..min(taskList.size - 1, countResult + offset)) {
            if (isComplete != null && taskList[i].isComplete != isComplete) {
                continue
            }
            if (datetimeBegin != null && taskList[i].datetimeBegin != "" &&
                DatetimeService.toDatetime(taskList[i].datetimeBegin) < datetimeBegin) {
                continue
            }
            if (datetimeEnd != null && taskList[i].datetimeEnd != "" &&
                DatetimeService.toDatetime(taskList[i].datetimeEnd) > datetimeEnd) {
                continue
            }
            if (formatResult != "" &&
                (taskList[i].name.findAnyOf(listOf(formatResult), 0, true) != null ||
                        taskList[i].description.findAnyOf(listOf(formatResult), 0, true) != null)) {
                continue
            }

            var project = projectMap[taskList[i].projectId]
            if (project == null) {
                project = projectDao.getProjectById(taskList[i].projectId)
                projectMap[taskList[i].projectId] = project!!
            }

            result.add(TaskRoomMapper.toDomain(
                taskRoomEntity = taskList[i],
                projectName = project.name)
            )
        }

        return result.toList()
    }

    override fun deleteTask(taskId: Long, status: Status?) {
        taskDao.deleteTasksAll()
//        taskDao.deleteTaskById(taskId)
    }
}

