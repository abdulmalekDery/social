package com.tessafold.social.features.postdetails

interface IUserItem {
    val id: Int
    val name: String?
    val userName: String?
    val email: String?
    val addressStreet: String?
    val addressSuite: String?
    val addressCity: String?
    val addressZipcode: String?
    val phone: String?
    val website: String?
    val companyName: String?
    val companyBs: String?
}