package com.tessafold.social.models.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tessafold.social.models.local.models.PostDB

@Dao
interface PostDBDao {

    @Query("SELECT * FROM post_table")
    fun getPosts(): List<PostDB>

    @Query("SELECT * FROM post_table WHERE id = :id")
    fun getPost(id: Int): PostDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: PostDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: ArrayList<PostDB>): LongArray

    @Query("DELETE FROM post_table")
    suspend fun deleteAll()

}