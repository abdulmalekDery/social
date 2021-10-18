package com.tessafold.social.models.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tessafold.social.models.common.IComment

@Entity(tableName = "comment_table")
data class CommentDB(
    @PrimaryKey override val id: Int,
    override val postId: Int,
    override val name: String?,
    override val email: String?,
    override val body: String?
): IComment
