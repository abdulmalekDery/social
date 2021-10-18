package com.tessafold.social.models.network.pojo.postdetails

import com.tessafold.social.models.common.IGeo

data class Geo(
    override val lat: String?,
    override val lng: String?
): IGeo
