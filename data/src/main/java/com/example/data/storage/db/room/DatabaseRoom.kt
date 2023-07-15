package com.example.data.storage.db.room

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

const val DATABASE_FILE_NAME = "database.db"

class DatabaseRoom {

    companion object {

        fun get(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_FILE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}