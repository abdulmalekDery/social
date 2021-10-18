package com.tessafold.social.features.posts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tessafold.social.R
import com.tessafold.social.base.BaseActivity
import com.tessafold.social.databinding.ActivityPostsBinding
import com.tessafold.social.features.postdetails.PostDetailsActivity
import com.tessafold.social.models.network.control.Resource
import com.tessafold.social.utils.Constants

class PostsActivity : BaseActivity() {

    private val viewModel: PostsViewModel by viewModels { viewModelFactory }
    private lateinit var binding: ActivityPostsBinding
    private lateinit var postsAdapter: PostsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
        setUpViews()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel.postsMutableLiveData.observe(this,  {
            when(it){
                is Resource.Loading -> {
                    binding.postsProgress.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.postsProgress.visibility = View.INVISIBLE
                    postsAdapter.dataSet = it.data!!
                    postsAdapter.notifyDataSetChanged()
                }
                else -> {
                    Toast.makeText(this,getString(R.string.network_error), Toast.LENGTH_SHORT).show()
                    binding.postsProgress.visibility = View.INVISIBLE
                }
            }
        })
        viewModel.getData()
    }

    private fun setUpViews() {
        title = getString(R.string.posts)
        val layoutManager = StaggeredGridLayoutManager(1,
            StaggeredGridLayoutManager.VERTICAL)
        binding.postsRecyclerView.layoutManager = layoutManager
        postsAdapter = PostsAdapter() { item, index ->
            val intent = Intent(this, PostDetailsActivity::class.java)
            intent.putExtra(Constants.POST_ID, item.id)
            startActivity(intent)
        }
        binding.postsRecyclerView.adapter = postsAdapter
    }

}