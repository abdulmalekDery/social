package com.tessafold.social.features.posts

import androidx.lifecycle.MutableLiveData
import com.tessafold.social.base.BaseViewModel
import com.tessafold.social.models.common.IPost
import com.tessafold.social.models.manager.DataRepository
import com.tessafold.social.models.network.control.Errors.Companion.NETWORK_ERROR
import com.tessafold.social.models.network.control.Resource
import com.tessafold.social.models.network.pojo.post.Post
import com.tessafold.social.models.network.pojo.postdetails.Comment
import com.tessafold.social.models.network.pojo.postdetails.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PostsViewModel@Inject constructor(
    val dataRepository: DataRepository,
    override val coroutineContext: CoroutineContext
) : BaseViewModel(), CoroutineScope {

    var postsMutableLiveData: MutableLiveData<Resource<List<PostItem>>> = MutableLiveData()


    fun getData(){
        postsMutableLiveData.postValue(Resource.Loading())
        launch {
            try {
                withContext(Dispatchers.IO)
                {
                    val localPosts = dataRepository.getLocalPosts()
                    if (localPosts.isEmpty())
                        callNetworkAndSaveData()
                    else {
                        sendDataToUi(localPosts)
                    }
                }
            } catch (e: Exception) {
                postsMutableLiveData.postValue(Resource.DataError(NETWORK_ERROR))
            }
        }
    }

    private suspend fun sendDataToUi(posts: List<IPost>) {
        val list = ArrayList<PostItem>()
        posts.forEach {
            val user = dataRepository.getLocalUser(it.userId)
            val post = PostItem(
                id = it.id,
                posterId = it.userId,
                title = it.title,
                body = it.body,
                posterName = user.name,
                posterUserName = user.username
            )
            list.add(post)
        }
        postsMutableLiveData.postValue(Resource.Success(data = list))
    }

    private suspend fun callNetworkAndSaveData() {
        try {
            var postsResponse: Resource<List<Post>>? = dataRepository.getRemotePosts()
            var usersResponse: Resource<List<User>>? = dataRepository.getRemoteUsers()
            var commentsResponse: Resource<List<Comment>>? = dataRepository.getRemoteComment()
            dataRepository.insertPosts(postsResponse?.data!!)
            dataRepository.insertUsers(usersResponse?.data!!)
            dataRepository.insertComments(commentsResponse?.data!!)
            sendDataToUi(postsResponse.data!!)
        } catch (e: Exception) {
            postsMutableLiveData.postValue(Resource.DataError(NETWORK_ERROR))
        }

    }
}