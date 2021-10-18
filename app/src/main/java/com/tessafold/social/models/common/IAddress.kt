package com.tessafold.social.models.common

interface IAddress {
    val street: String?
    val suite: String?
    val city: String?
    val zipcode: String?
    val geo: IGeo?
}