package com.tessafold.social.models.network.pojo.postdetails

import com.tessafold.social.models.common.IAddress
import com.tessafold.social.models.common.ICompany
import com.tessafold.social.models.common.IUser

data class User(
    override val id: Int,
    override val name: String?,
    override val username: String?,
    override val email: String?,
    override val address: Address?,
    override val phone: String?,
    override val website: String?,
    override val company: Company?

): IUser
