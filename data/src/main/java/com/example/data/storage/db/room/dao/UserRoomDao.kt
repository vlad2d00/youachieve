package com.example.data.storage.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.storage.db.room.entities.UserRoomEntity

@Dao
interface UserRoomDao {

    // INSERT

    @Insert
    fun insertUser(userRoomEntity: UserRoomEntity)


    // UPDATE

    @Update
    fun updateUser(userRoomEntity: UserRoomEntity)


    // GET

    @Query("SELECT * FROM users " +
            "ORDER BY first_name, last_name;")
    fun getUserListAll(): List<UserRoomEntity>?

    @Query("SELECT * FROM users " +
            "WHERE id = :userId " +
            "LIMIT 1;")
    fun getUserById(userId: Long): UserRoomEntity?


    // DELETE

    @Query("DELETE FROM users;")
    fun deleteUsersAll()

    @Query("DELETE FROM users " +
            "WHERE id = :userId;")
    fun deleteUserById(userId: Long)

}