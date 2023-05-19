package com.example.kotlindemo.dtos

import com.example.kotlindemo.model.PostPets

data class UserReturnDTO (
    val id: Long,

    val name: String,

    val email: String,

    val birth: String,

    val location: String,

    val profilePic: String,

    val banner: String,

    val phone: String,

    val instagram: String?,

    val facebook: String?,

    val whatsapp: String?,

    val postPets: MutableList<PostPets> = mutableListOf()
)