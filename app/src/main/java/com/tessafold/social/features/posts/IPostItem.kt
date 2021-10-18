package com.tessafold.social.features.posts

interface IPostItem {
    val id: Int
    val posterId: Int
    val title: String?
    val body: String?
    val posterName: String?
    val posterUserName: String?
}