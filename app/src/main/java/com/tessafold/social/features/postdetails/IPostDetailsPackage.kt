package com.tessafold.social.features.postdetails


interface IPostDetailsPackage {
    val post: IPostDetailsItem?
    val user: IUserItem?
    val comments: List<ICommentItem>
}