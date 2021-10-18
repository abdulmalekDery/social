package com.tessafold.social.models.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tessafold.social.models.common.IAddress

@Entity(tableName = "address_table")
data class AddressDB(
    @PrimaryKey(autoGenerate = true) val addressId: Int = 0,
    override val street: String?,
    override val suite: String?,
    override val city: String?,
    override val zipcode: String?,
    @Embedded override val geo: GeoDB?
): IAddress
