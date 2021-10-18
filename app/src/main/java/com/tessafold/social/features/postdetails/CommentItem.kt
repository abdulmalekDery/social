package com.tessafold.social.features.postdetails

data class CommentItem(
    override val id: Int,
    override val name: String?,
    override val email: String?,
    override val body: String?

): ICommentItem
