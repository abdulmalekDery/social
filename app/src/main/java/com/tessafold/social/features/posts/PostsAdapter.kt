package com.tessafold.social.features.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tessafold.social.R
import com.tessafold.social.databinding.ItemPostBinding
import com.tessafold.social.dependencyinjection.module.GlideApp

class PostsAdapter(var dataSet: List<IPostItem> = emptyList(),
                    val listener: (item : IPostItem, index : Int)->Unit):
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(item: IPostItem) {
            binding.postItem = item
            binding.executePendingBindings()
        }

        init {
            binding.postItemViewGroup.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
                listener(dataSet[adapterPosition],adapterPosition,)
        }
    }

    override fun getItemCount() = dataSet.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]

        GlideApp.with(holder.binding.userImage.context).load(R.mipmap.avatar)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop()
            .into(holder.binding.userImage)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(layoutInflater, parent as ViewGroup, false)
        return ViewHolder(binding)
    }

}