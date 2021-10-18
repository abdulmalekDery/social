package com.tessafold.social.models.manager

import com.tessafold.social.models.common.IComment
import com.tessafold.social.models.common.IPost
import com.tessafold.social.models.common.IUser
import com.tessafold.social.models.local.LocalRepository
import com.tessafold.social.models.local.models.*
import com.tessafold.social.models.network.control.RemoteRepository
import com.tessafold.social.models.network.control.Resource
import com.tessafold.social.models.network.pojo.post.Post
import com.tessafold.social.models.network.pojo.postdetails.Comment
import com.tessafold.social.models.network.pojo.postdetails.User
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class DataRepository @Inject
constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    override val coroutineContext: CoroutineContext
) : DataSource, CoroutineScope {

    override suspend fun getRemotePosts(): Resource<List<Post>> {
        return remoteRepository.getPosts()
    }

    override suspend fun getRemoteUsers(): Resource<List<User>> {
        return remoteRepository.getUsers()
    }

    override suspend fun getRemoteComment(): Resource<List<Comment>> {
        return remoteRepository.getComments()
    }

    override suspend fun insertPosts(posts: List<Post>) {
        posts.forEach {
            val postDb = PostDB(
                id = it.id,
                userId = it.userId,
                title = it.title,
                body = it.body,
            )
            localRepository.insertPost(postDb)
        }
    }

    override suspend fun insertUsers(users: List<User>) {
        users.forEach {
            val userDb = UserDB(
                id = it.id,
                name = it.name,
                username = it.username,
                email = it.email,
                phone = it.phone,
                website = it.website,
                address = AddressDB(
                    street = it.address?.street,
                    suite = it.address?.suite,
                    city = it.address?.city,
                    zipcode = it.address?.zipcode,
                    geo = GeoDB(
                      lat = it.address?.geo?.lat,
                      lng = it.address?.geo?.lng
                    )
                ),
                company = CompanyDB(
                    name = it.company?.name,
                    bs = it.company?.bs,
                    catchPhrase = it.company?.catchPhrase
                )
            )
            localRepository.insertUser(userDb)
        }
    }

    override suspend fun insertComments(comments: List<Comment>) {
        comments.forEach {
            val commentDb = CommentDB(
                id = it.id,
                body = it.body,
                postId = it.postId,
                name = it.name,
                email = it.email
            )
            localRepository.insertComment(commentDb)
        }
    }


    override fun getLocalPost(postId: Int): IPost {
        return localRepository.getPost(postId)
    }

    override fun getLocalUser(userId: Int): IUser {
        return localRepository.getUser(userId)
    }

    override fun getLocalCommentsOfPost(postId: Int): List<IComment> {
        return localRepository.getCommentsOfPost(postId)
    }

    override fun getLocalPosts(): List<IPost> {
        return localRepository.getPosts()
    }

}
