package com.example.data.storage.db.room.interfaces

import android.content.Context
import com.example.data.models.ProjectDto
import com.example.data.models.TaskDto
import com.example.data.storage.db.room.DatabaseRoom
import com.example.data.storage.db.room.entities.ProjectRoomEntity
import com.example.data.storage.db.room.entities.TaskRoomEntity
import com.example.data.storage.db.room.mapper.ProjectMapperRoom
import com.example.data.storage.db.room.mapper.TaskMapperRoom
import com.example.data.storage.interfaces.TaskStorage
import com.example.domain.models.Status
import com.example.domain.utils.Datetime
import java.time.LocalDateTime

class TaskDbRoom(context: Context): TaskStorage {

    private val taskDao = DatabaseRoom.get(context).getTaskDao()


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
                datetimeBegin = if (datetimeBegin == null) "" else Datetime.toString(datetimeBegin),
                datetimeEnd = if (datetimeEnd == null) "" else Datetime.toString(datetimeEnd),
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
            task.datetimeBegin = Datetime.toString(datetimeBegin)

        if (datetimeEnd != null)
            task.datetimeEnd = Datetime.toString(datetimeEnd)

        if (isComplete != null)
            task.isComplete = isComplete

        taskDao.updateTask(task)
    }

    override fun getTask(taskId: Long, status: Status?): TaskDto? {
        val task = taskDao.getTaskById(taskId) ?: return null
        return TaskMapperRoom.toDto(task)
    }

    override fun getTaskList(
        projectId: Long,
        format: String?,
        datetimeBegin: LocalDateTime?,
        datetimeEnd: LocalDateTime?,
        isComplete: Boolean?,
        status: Status?
    ): List<TaskDto>? {
        val items = taskDao.getTaskListByProjectId(projectId) ?: return null
        val result = arrayListOf<TaskDto>()
        val formatNew = format?.trim() ?: ""

        for (item in items) {
            if (isComplete != null && item.isComplete != isComplete) {
                continue
            }
            if (datetimeBegin != null && item.datetimeBegin != "" &&
                Datetime.toDatetime(item.datetimeBegin) < datetimeBegin) {
                continue
            }
            if (datetimeEnd != null && item.datetimeEnd != "" &&
                Datetime.toDatetime(item.datetimeEnd) > datetimeEnd) {
                continue
            }
            if (formatNew != "" &&
                (item.name.findAnyOf(listOf(formatNew), 0, true) != null ||
                        item.description.findAnyOf(listOf(formatNew), 0, true) != null)) {
                continue
            }
            result.add(TaskMapperRoom.toDto(item))
        }

        return result.toList()
    }

    override fun deleteTask(taskId: Long, status: Status?) {
        taskDao.deleteTasksAll()
//        taskDao.deleteTaskById(taskId)
    }
}

