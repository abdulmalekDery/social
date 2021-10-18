package com.tessafold.social.features.postdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.tessafold.social.R
import com.tessafold.social.databinding.ItemCommentBinding
import com.tessafold.social.databinding.ItemUserDetailsBinding
import com.tessafold.social.dependencyinjection.module.GlideApp

class ItemCommentView: AbstractBindingItem<ItemCommentBinding>() {

    var commentModel: ICommentItem? = null

    fun withData(commentModel: ICommentItem): ItemCommentView {
        this.commentModel = commentModel
        return this
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemCommentBinding {
        return ItemCommentBinding.inflate(inflater, parent, false)
    }

    override val type: Int
        get() = R.id.item_comment

    override fun bindView(binding: ItemCommentBinding, payloads: List<Any>) {
        GlideApp.with(binding.commentUserImage.context).load(R.mipmap.avatar)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop()
            .into(binding.commentUserImage)
        binding.commentItem= commentModel
        binding.executePendingBindings()
    }
}