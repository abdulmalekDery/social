package com.tessafold.social.features.postdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.tessafold.social.R
import com.tessafold.social.databinding.ItemPostDetailsBinding
import com.tessafold.social.features.posts.IPostItem

class ItemPostDetailsView: AbstractBindingItem<ItemPostDetailsBinding>() {

    var postModel: IPostDetailsItem? = null

    fun withData(postModel: IPostDetailsItem): ItemPostDetailsView {
        this.postModel = postModel
        return this
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemPostDetailsBinding {
        return ItemPostDetailsBinding.inflate(inflater, parent, false)
    }

    override val type: Int
        get() = R.id.item_post

    override fun bindView(binding: ItemPostDetailsBinding, payloads: List<Any>) {
        binding.postDetailsItem= postModel
        binding.executePendingBindings()
    }
}