package com.example.kotlindemo.dtos

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class LoginDTO(

  @field:NotEmpty(message = "this field can not be empty")
  @field:Email(message = "this field can not be a invalid email")
  val email: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val password: String = ""
)
