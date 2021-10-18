package com.tessafold.social.models.network.control

import com.tessafold.social.base.BaseRepository
import com.tessafold.social.models.network.pojo.post.Post
import com.tessafold.social.models.network.pojo.postdetails.Comment
import com.tessafold.social.models.network.pojo.postdetails.User
import com.tessafold.social.models.network.services.IApiService
import retrofit2.HttpException
import javax.inject.Inject

class RemoteRepository @Inject
constructor(private val apiService: IApiService) : BaseRepository() {


    suspend fun getPosts(): Resource<List<Post>> {

        var res:Resource<List<Post>>

        try {
            val response = apiService.getPosts()
            val pos = 0
            if (response.isSuccessful) {
                res = Resource.Success(data = response.body() as List<Post>)
            } else {
                res = Resource.DataError(errorCode = response.code())
            }
        } catch (e: HttpException) {
            res = Resource.Exception(errorMessage = "error")
        } catch (e: Throwable) {
            res = Resource.Exception(errorMessage = "error")
        }
        return res
    }

    suspend fun getComments(): Resource<List<Comment>> {

        var res:Resource<List<Comment>>

        try {
            val response = apiService.getComments()
            val pos = 0
            if (response.isSuccessful) {
                res = Resource.Success(data = response.body() as List<Comment>)
            } else {
                res = Resource.DataError(errorCode = response.code())
            }
        } catch (e: HttpException) {
            res = Resource.Exception(errorMessage = "error")
        } catch (e: Throwable) {
            res = Resource.Exception(errorMessage = "error")
        }
        return res
    }

    suspend fun getUsers(): Resource<List<User>> {

        var res:Resource<List<User>>

        try {
            val response = apiService.getUsers()
            val pos = 0
            if (response.isSuccessful) {
                res = Resource.Success(data = response.body() as List<User>)
            } else {
                res = Resource.DataError(errorCode = response.code())
            }
        } catch (e: HttpException) {
            res = Resource.Exception(errorMessage = "error")
        } catch (e: Throwable) {
            res = Resource.Exception(errorMessage = "error")
        }
        return res
    }


}