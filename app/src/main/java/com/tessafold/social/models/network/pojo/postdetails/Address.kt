package com.tessafold.social.models.network.pojo.postdetails

import com.tessafold.social.models.common.IAddress
import com.tessafold.social.models.common.IGeo

data class Address(
    override val street: String?,
    override val suite: String?,
    override val city: String?,
    override val zipcode: String?,
    override val geo: Geo?

): IAddress
