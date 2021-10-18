package com.tessafold.social.features.posts

data class PostItem(
    override val id: Int,
    override val posterId: Int,
    override val title: String?,
    override val body: String?,
    override val posterName: String?,
    override val posterUserName: String?

): IPostItem
