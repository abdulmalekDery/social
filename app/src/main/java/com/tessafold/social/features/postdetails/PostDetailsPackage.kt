package com.tessafold.social.features.postdetails

data class PostDetailsPackage(
    override val post: IPostDetailsItem?,
    override val user: IUserItem?,
    override val comments: List<ICommentItem>
): IPostDetailsPackage
