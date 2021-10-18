package com.tessafold.social.models.network.services

import com.tessafold.social.config.Urls
import com.tessafold.social.models.network.control.ResponseWrapper
import com.tessafold.social.models.network.pojo.post.Post
import com.tessafold.social.models.network.pojo.postdetails.Comment
import com.tessafold.social.models.network.pojo.postdetails.User
import retrofit2.Response
import retrofit2.http.*

interface IApiService {

    @GET(Urls.POSTS)
    suspend fun getPosts(): Response<List<Post>>

    @GET(Urls.COMMENTS)
    suspend fun getComments(): Response<List<Comment>>

    @GET(Urls.USERS)
    suspend fun getUsers(): Response<List<User>>
}