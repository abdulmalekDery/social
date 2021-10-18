package com.tessafold.social.models.common

interface IComment {
    val id: Int
    val postId: Int
    val name: String?
    val email: String?
    val body: String?
}