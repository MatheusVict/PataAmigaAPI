package com.example.kotlindemo.dtos

import com.example.kotlindemo.model.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CreateUserDTO(

  @field:NotEmpty(message = "this field can not be empty")
  val name: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  @field:Email(message = "this field can not be a invalid email")
  val email: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  var password: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val birth: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val location: String = "",

  val profilePic: String? = "",

  val banner: String? = "",

  @field:NotEmpty(message = "this field can not be empty")
  val phone: String = "",

  val instagram: String? = "",

  val facebook: String? = "",

  val whatsapp: String? = "",

  ) {
  fun toEntity(): User = User(
    name = this.name,
    email = this.email,
    password = this.password,
    birth = this.birth,
    location = this.location,
    profilePic = this.profilePic,
    banner = this.banner,
    phone = this.phone,
    instagram = this.instagram,
    facebook = this.facebook,
    whatsapp = this.whatsapp
  )
}
