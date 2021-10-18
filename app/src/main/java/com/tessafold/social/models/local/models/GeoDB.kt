package com.tessafold.social.models.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tessafold.social.models.common.IGeo

@Entity(tableName = "geo_table")
data class GeoDB(
    @PrimaryKey(autoGenerate = true) val geoId: Int = 0,
    override val lat: String?,
    override val lng: String?
): IGeo
