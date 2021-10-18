package com.tessafold.social.models.network.pojo.postdetails

import com.tessafold.social.models.common.ICompany

data class Company(
    override val name: String?,
    override val catchPhrase: String?,
    override val bs: String?

): ICompany
