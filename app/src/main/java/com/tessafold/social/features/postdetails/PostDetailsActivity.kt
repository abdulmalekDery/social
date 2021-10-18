package com.tessafold.social.features.postdetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.tessafold.social.R
import com.tessafold.social.base.BaseActivity
import com.tessafold.social.databinding.ActivityPostDetailsBinding
import com.tessafold.social.databinding.ActivityPostsBinding
import com.tessafold.social.features.posts.PostsAdapter
import com.tessafold.social.models.network.control.Resource
import com.tessafold.social.utils.Constants

class PostDetailsActivity : BaseActivity() {

    private val viewModel: PostDetailsViewModel by viewModels { viewModelFactory }
    private lateinit var binding: ActivityPostDetailsBinding
    private lateinit var fastItemAdapter: FastItemAdapter<IItem<*>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_details)
        setUpViews()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel.postMutableLiveData.observe(this,  {
            when(it){
                is Resource.Loading -> {
                    binding.postsDetailsProgress.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.postsDetailsProgress.visibility = View.INVISIBLE
                    fastItemAdapter.clear()
                    fastItemAdapter.add(ItemPostDetailsView().withData(it.data?.post!!))
                    fastItemAdapter.add(ItemHeaderView().withData(ItemHeader(getString(R.string.written_by))))
                    fastItemAdapter.add(ItemProfileView().withData(it.data?.user!!))
                    fastItemAdapter.add(ItemHeaderView().withData(ItemHeader(getString(R.string.comments))))
                    it.data?.comments?.forEach { comment ->
                        fastItemAdapter.add(ItemCommentView().withData(comment))
                    }

                }
                else -> {
                    Toast.makeText(this,getString(R.string.local_error), Toast.LENGTH_SHORT).show()
                    binding.postsDetailsProgress.visibility = View.INVISIBLE
                }
            }


        })

        viewModel.getData(intent.getIntExtra(Constants.POST_ID,-1))
    }

    private fun setUpViews() {
        title = getString(R.string.post_details)
        val layoutManager = StaggeredGridLayoutManager(1,
            StaggeredGridLayoutManager.VERTICAL)
        binding.postsDetailsRecyclerView.layoutManager = layoutManager
        fastItemAdapter = FastItemAdapter<IItem<*>>()
        binding.postsDetailsRecyclerView.adapter = fastItemAdapter
    }
}