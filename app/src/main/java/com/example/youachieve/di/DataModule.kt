package com.example.youachieve.di

import android.content.Context
import com.example.data.repository.*
import com.example.data.storage.db.room.interfaces.ProjectDbRoom
import com.example.data.storage.db.room.interfaces.TaskDbRoom
import com.example.data.storage.db.room.interfaces.WorkspaceDbRoom
import com.example.data.storage.interfaces.*
import com.example.data.storage.ram.MainSectionRam
import com.example.data.storage.ram.WorkspaceUiDataRam
import com.example.data.storage.ram.WorkspaceSectionRam
import com.example.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMainSectionStorage(): MainSectionStorage {
        return MainSectionRam()
    }

    @Provides
    @Singleton
    fun provideMainSectionRepository(
        mainSectionStorage: MainSectionStorage
    ): MainSectionRepository {
        return MainSectionRepositoryImpl(mainSectionStorage = mainSectionStorage)
    }

    @Provides
    @Singleton
    fun provideWorkspaceSectionStorage(): WorkspaceSectionStorage {
        return WorkspaceSectionRam()
    }

    @Provides
    @Singleton
    fun provideWorkspaceSectionRepository(
        workspaceSectionStorage: WorkspaceSectionStorage
    ): WorkspaceSectionRepository {
        return WorkspaceSectionRepositoryImpl(workspaceSectionStorage = workspaceSectionStorage)
    }

    @Provides
    @Singleton
    fun provideWorkspaceUiDataStorage(): WorkspaceUiDataStorage {
        return WorkspaceUiDataRam()
    }

    @Provides
    @Singleton
    fun provideWorkspaceDataRepository(
        workspaceUiDataStorage: WorkspaceUiDataStorage
    ): WorkspaceUiDataRepository {
        return WorkspaceUiDataRepositoryImpl(workspaceUiDataStorage = workspaceUiDataStorage)
    }

    @Provides
    @Singleton
    fun provideWorkspaceStorage(@ApplicationContext context: Context): WorkspaceStorage {
        return WorkspaceDbRoom(context)
    }

    @Provides
    @Singleton
    fun provideWorkspaceRepository(
        workspaceStorage: WorkspaceStorage
    ): WorkspaceRepository {
        return WorkspaceRepositoryImpl(workspaceStorage = workspaceStorage)
    }

    @Provides
    @Singleton
    fun provideProjectStorage(@ApplicationContext context: Context): ProjectStorage {
        return ProjectDbRoom(context)
    }

    @Provides
    @Singleton
    fun provideProjectRepository(
        projectStorage: ProjectStorage
    ): ProjectRepository {
        return ProjectRepositoryImpl(projectStorage = projectStorage)
    }

    @Provides
    @Singleton
    fun provideTaskStorage(@ApplicationContext context: Context): TaskStorage {
        return TaskDbRoom(context)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        taskStorage: TaskStorage
    ): TaskRepository {
        return TaskRepositoryImpl(taskStorage = taskStorage)
    }

}