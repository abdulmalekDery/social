package com.tessafold.social.models.network.pojo.post

import com.tessafold.social.models.common.IPost

data class Post(
    override val id: Int,
    override val userId: Int,
    override val title: String?,
    override val body: String?
): IPost