package com.tessafold.social.models.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tessafold.social.models.common.ICompany

@Entity(tableName = "company_table")
data class CompanyDB(
    @PrimaryKey(autoGenerate = true) val CompanyId: Int = 0,
    @ColumnInfo(name= "companyName") override val name: String?,
    override val catchPhrase: String?,
    override val bs: String?
): ICompany
