package com.tessafold.social.features.postdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.tessafold.social.R
import com.tessafold.social.databinding.ItemPostDetailsBinding
import com.tessafold.social.databinding.ItemUserDetailsBinding
import com.tessafold.social.dependencyinjection.module.GlideApp
import com.tessafold.social.features.posts.IPostItem

class ItemProfileView: AbstractBindingItem<ItemUserDetailsBinding>() {

    var userModel: IUserItem? = null

    fun withData(userModel: IUserItem): ItemProfileView {
        this.userModel = userModel
        return this
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemUserDetailsBinding {
        return ItemUserDetailsBinding.inflate(inflater, parent, false)
    }

    override val type: Int
        get() = R.id.item_user_profile

    override fun bindView(binding: ItemUserDetailsBinding, payloads: List<Any>) {
        GlideApp.with(binding.userImageView.context).load(R.mipmap.avatar)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop()
            .into(binding.userImageView)
        binding.userItem= userModel
        binding.executePendingBindings()
    }
}