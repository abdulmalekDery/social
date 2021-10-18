package com.tessafold.social.models.local

import androidx.annotation.WorkerThread
import com.tessafold.social.models.common.IComment
import com.tessafold.social.models.common.IUser
import com.tessafold.social.models.local.dao.CommentDBDao
import com.tessafold.social.models.local.dao.PostDBDao
import com.tessafold.social.models.local.dao.UserDBDao
import com.tessafold.social.models.local.models.CommentDB
import com.tessafold.social.models.local.models.PostDB
import com.tessafold.social.models.local.models.UserDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepository @Inject
constructor(
    private val postDBDao: PostDBDao,
    private val commentDBDao: CommentDBDao,
    private val userDBDao: UserDBDao,
) {

    fun getPosts(): List<PostDB>{
        return postDBDao.getPosts()
    }

    fun getUser(id: Int): UserDB{
        return userDBDao.getUser(id)
    }

    fun getCommentsOfPost(postId: Int): List<CommentDB>{
        return commentDBDao.getCommentsOfPost(postId)
    }

    fun getPostUser(postId: Int): UserDB{
        val post = postDBDao.getPost(postId)
        return userDBDao.getUser(post.userId)
    }


    fun getPost(postId: Int): PostDB{
        return postDBDao.getPost(postId)
    }

    fun insertPostsList(postsDBList: ArrayList<PostDB>): LongArray {
        return postDBDao.insertAll(postsDBList)
    }

    fun insertUsersList(usersDBList: ArrayList<UserDB>): LongArray {
        return userDBDao.insertAll(usersDBList)
    }

    fun insertCommentsList(commentsDBList: ArrayList<CommentDB>): LongArray {
        return commentDBDao.insertAll(commentsDBList)
    }

    suspend fun insertPost(postDB: PostDB) {
        postDBDao.insert(postDB)
    }

    suspend fun insertUser(userDB: UserDB) {
        userDBDao.insert(userDB)
    }

    suspend fun insertComment(commentDB: CommentDB) {
        commentDBDao.insert(commentDB)
    }

    suspend fun deleteAllPosts(){
        postDBDao.deleteAll()
    }

    suspend fun deleteAllUsers(){
        userDBDao.deleteAll()
    }

    suspend fun deleteAllComments(){
        commentDBDao.deleteAll()
    }
}