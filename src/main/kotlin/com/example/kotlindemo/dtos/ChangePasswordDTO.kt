package com.example.kotlindemo.dtos

import javax.validation.constraints.NotEmpty

data class ChangePasswordDTO(

  @field:NotEmpty(message = "this field can not be empty")
  val email: String,

  @field:NotEmpty(message = "this field can not be empty")
  val newPassword: String
)
