package com.tessafold.social.features.postdetails

data class UserItem(
    override val id: Int,
    override val name: String?,
    override val userName: String?,
    override val email: String?,
    override val phone: String?,
    override val website: String?,
    override val addressStreet: String?,
    override val addressSuite: String?,
    override val addressCity: String?,
    override val addressZipcode: String?,
    override val companyName: String?,
    override val companyBs: String?
):IUserItem
