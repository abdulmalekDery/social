package com.tessafold.social.models.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tessafold.social.models.common.IPost
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "post_table")
data class PostDB (
    @PrimaryKey override val id: Int,
    override val userId: Int,
    override val title: String?,
    override val body: String?,
): IPost