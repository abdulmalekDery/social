package com.tessafold.social.models.common

interface IPost {
    val id: Int
    val userId: Int
    val title: String?
    val body: String?
}