package com.tessafold.social.features.postdetails

data class PostDetailsItem(
    override val id: Int,
    override val title: String?,
    override val body: String?
): IPostDetailsItem
