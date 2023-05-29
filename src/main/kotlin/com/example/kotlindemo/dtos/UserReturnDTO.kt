package com.example.kotlindemo.dtos

import com.example.kotlindemo.model.PostPets
import com.example.kotlindemo.model.User

data class UserReturnDTO(
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
) {
  constructor(user: User) : this(
    id = user.id,
    name = user.name,
    email = user.email,
    facebook = user.facebook,
    instagram = user.instagram,
    banner = user.banner,
    location = user.location,
    whatsapp = user.whatsapp,
    phone = user.phone,
    profilePic = user.profilePic,
    birth = user.birth
  )
}