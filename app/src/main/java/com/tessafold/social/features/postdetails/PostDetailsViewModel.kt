package com.tessafold.social.features.postdetails

import androidx.lifecycle.MutableLiveData
import com.tessafold.social.base.BaseViewModel
import com.tessafold.social.models.common.IComment
import com.tessafold.social.models.common.IPost
import com.tessafold.social.models.common.IUser
import com.tessafold.social.models.manager.DataRepository
import com.tessafold.social.models.network.control.Errors
import com.tessafold.social.models.network.control.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PostDetailsViewModel @Inject constructor(
    val dataRepository: DataRepository,
    override val coroutineContext: CoroutineContext
) : BaseViewModel(), CoroutineScope {
    var postMutableLiveData: MutableLiveData<Resource<IPostDetailsPackage>> = MutableLiveData()

    fun getData(postId: Int) {
        postMutableLiveData.postValue(Resource.Loading())
        launch {
            withContext(Dispatchers.IO)
            {
                try {
                val post: IPost = dataRepository.getLocalPost(postId)
                val user: IUser = dataRepository.getLocalUser(post.userId)
                val comments: List<IComment> = dataRepository.getLocalCommentsOfPost(postId)

                val postUI: IPostDetailsItem = getPostUIItem(post)
                val userUI: IUserItem = getUserUIItem(user)
                val commentsUI: List<ICommentItem> = getCommentsUIItems(comments)
                val uiPackage: IPostDetailsPackage = PostDetailsPackage(
                    post = postUI,
                    user = userUI,
                    comments = commentsUI
                )
                    postMutableLiveData.postValue(Resource.Success(data = uiPackage))
                } catch (e: Exception){
                    postMutableLiveData.postValue(Resource.DataError(Errors.DEFAULT_ERROR))
                }
            }
        }

    }

    private suspend fun getCommentsUIItems(comments: List<IComment>): List<ICommentItem> {
        val list = ArrayList<ICommentItem>()
        comments.forEach {
            val comment = CommentItem(
                id = it.id,
                name = it.name,
                email = it.email,
                body = it.body
            )
            list.add(comment)
        }
        return list
    }

    private suspend fun getUserUIItem(user: IUser): IUserItem {
        return UserItem(
            id = user.id,
            name = user.name,
            userName = user.username,
            email = user.email,
            phone = user.phone,
            website = user.website,
            addressStreet = user.address?.street,
            addressSuite = user.address?.suite,
            addressCity = user.address?.city,
            addressZipcode = user.address?.zipcode,
            companyName = user.company?.name,
            companyBs = user.company?.bs
        )
    }

    private suspend fun getPostUIItem(post: IPost): IPostDetailsItem {
        return PostDetailsItem(
            id = post.id,
            title = post.title,
            body = post.body
        )
    }

}