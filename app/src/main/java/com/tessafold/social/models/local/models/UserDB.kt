package com.tessafold.social.models.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tessafold.social.models.common.IUser

@Entity(tableName = "user_table")
data class UserDB(
    @PrimaryKey override val id: Int,
    override val name: String?,
    override val username: String?,
    override val email: String?,
    @Embedded override val address: AddressDB?,
    override val phone: String?,
    override val website: String?,
    @Embedded override val company: CompanyDB?

): IUser
