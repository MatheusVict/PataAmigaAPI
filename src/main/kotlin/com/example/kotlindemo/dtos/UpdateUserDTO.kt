package com.example.kotlindemo.dtos

import com.example.kotlindemo.model.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class UpdateUserDTO(
  @field:NotEmpty(message = "this field can not be empty")
  val name: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val location: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val profilePic: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val banner: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val phone: String = "",

  val instagram: String? = "",

  val facebook: String? = "",

  val whatsapp: String? = "",
) {
  fun toEntity(user: User): User {
    user.name = this.name
    user.location = this.location
    user.profilePic = this.profilePic
    user.banner = this.banner
    user.phone = this.phone
    user.instagram = this.instagram
    user.facebook = this.facebook
    user.whatsapp = this.whatsapp
    return user
  }
}
