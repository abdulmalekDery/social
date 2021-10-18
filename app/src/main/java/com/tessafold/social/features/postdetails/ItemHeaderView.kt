package com.tessafold.social.features.postdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.tessafold.social.R
import com.tessafold.social.databinding.ItemHeaderBinding

class ItemHeaderView: AbstractBindingItem<ItemHeaderBinding>() {

    var headerModel: IHeaderItem? = null

    fun withData(headerModel: IHeaderItem): ItemHeaderView {
        this.headerModel = headerModel
        return this
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemHeaderBinding {
        return ItemHeaderBinding.inflate(inflater, parent, false)
    }

    override val type: Int
        get() = R.id.item_header

    override fun bindView(binding: ItemHeaderBinding, payloads: List<Any>) {
        binding.header= headerModel
        binding.executePendingBindings()
    }
}