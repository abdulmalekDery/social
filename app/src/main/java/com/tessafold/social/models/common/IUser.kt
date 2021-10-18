package com.tessafold.social.models.common

interface IUser {
    val id: Int
    val name: String?
    val username: String?
    val email: String?
    val address: IAddress?
    val phone: String?
    val website: String?
    val company: ICompany?
}