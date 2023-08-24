package com.example.youachieve.di

import android.content.Context
import com.example.data.repository.*
import com.example.data.storage.db.room.interfaces.ProjectRoomDb
import com.example.data.storage.db.room.interfaces.TaskRoomDb
import com.example.data.storage.db.room.interfaces.WorkspaceRoomDb
import com.example.data.storage.db.sharedprefs.AuthorizationSharedPrefs
import com.example.data.storage.interfaces.*
import com.example.data.storage.memory.DisplayMemory
import com.example.data.storage.memory.MainSectionMemory
import com.example.data.storage.memory.WorkspaceUiDataMemory
import com.example.data.storage.memory.WorkspaceSectionMemory
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
        return MainSectionMemory()
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
        return WorkspaceSectionMemory()
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
        return WorkspaceUiDataMemory()
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
        return WorkspaceRoomDb(context)
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
        return ProjectRoomDb(context)
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
        return TaskRoomDb(context)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        taskStorage: TaskStorage
    ): TaskRepository {
        return TaskRepositoryImpl(taskStorage = taskStorage)
    }

    @Provides
    @Singleton
    fun provideDisplayStorage(): DisplayStorage {
        return DisplayMemory()
    }

    @Provides
    @Singleton
    fun provideDisplayRepository(
        displayStorage: DisplayStorage
    ): DisplayRepository {
        return DisplayRepositoryImpl(displayStorage = displayStorage)
    }

    @Provides
    @Singleton
    fun provideAuthorizationStorage(@ApplicationContext context: Context): AuthorizationStorage {
        return AuthorizationSharedPrefs(context = context)
    }

    @Provides
    @Singleton
    fun provideAuthorizationRepository(
        authorizationStorage: AuthorizationStorage
    ): AuthorizationRepository {
        return AuthorizationRepositoryImpl(authorizationStorage = authorizationStorage)
    }

}