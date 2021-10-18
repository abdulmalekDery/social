package com.tessafold.social.models.manager

import com.tessafold.social.models.common.IComment
import com.tessafold.social.models.common.IPost
import com.tessafold.social.models.common.IUser
import com.tessafold.social.models.local.models.PostDB
import com.tessafold.social.models.network.control.Resource
import com.tessafold.social.models.network.pojo.post.Post
import com.tessafold.social.models.network.pojo.postdetails.Comment
import com.tessafold.social.models.network.pojo.postdetails.User


interface DataSource {
    suspend fun getRemotePosts(): Resource<List<Post>>
    suspend fun getRemoteUsers(): Resource<List<User>>
    suspend fun getRemoteComment(): Resource<List<Comment>>
    suspend fun insertPosts(posts: List<Post>)
    suspend fun insertUsers(users: List<User>)
    suspend fun insertComments(comments: List<Comment>)
    fun getLocalPost(postId: Int): IPost
    fun getLocalUser(userId: Int): IUser
    fun getLocalCommentsOfPost(postId: Int): List<IComment>
    fun getLocalPosts(): List<IPost>
}
