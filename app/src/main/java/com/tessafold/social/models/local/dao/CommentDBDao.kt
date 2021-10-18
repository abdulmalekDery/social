package com.tessafold.social.models.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tessafold.social.models.local.models.CommentDB
import com.tessafold.social.models.local.models.UserDB

@Dao
interface CommentDBDao {
    @Query("SELECT * FROM comment_table WHERE postId = :postId")
    fun getCommentsOfPost(postId: Int): List<CommentDB>

    @Query("SELECT * FROM comment_table WHERE id = :id")
    fun getComment(id: Int): CommentDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comment: CommentDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(comments: ArrayList<CommentDB>): LongArray

    @Query("DELETE FROM comment_table")
    suspend fun deleteAll()
}