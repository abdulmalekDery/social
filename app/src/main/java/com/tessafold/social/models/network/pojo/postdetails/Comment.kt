package com.tessafold.social.models.network.pojo.postdetails

import com.tessafold.social.models.common.IComment

data class Comment(
    override val id: Int,
    override val postId: Int,
    override val name: String?,
    override val email: String?,
    override val body: String?
): IComment
