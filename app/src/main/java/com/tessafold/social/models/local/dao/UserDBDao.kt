package com.tessafold.social.models.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tessafold.social.models.local.models.UserDB

@Dao
interface UserDBDao {

    @Query("SELECT * FROM user_table")
    fun getUsers(): List<UserDB>

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUser(id: Int): UserDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: ArrayList<UserDB>): LongArray

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}