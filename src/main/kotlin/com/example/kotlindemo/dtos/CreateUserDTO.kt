package com.example.kotlindemo.dtos

import org.springframework.lang.Nullable
import javax.persistence.Column
import javax.validation.constraints.NotBlank

data class CreateUserDTO (
    val name: String = "",

    val email: String = "",

    var password: String = "",

    val birth: String = "",

    val location: String = "",

    val profilePic: String = "",

    @get: NotBlank
    val banner: String = "",

    val phone: String = "",

    val instagram: String?,

    val facebook: String?,

    val whatsapp: String?,

    )