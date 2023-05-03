package com.example.kotlindemo.interfaces

import org.springframework.lang.Nullable
import org.springframework.web.multipart.MultipartFile
import javax.persistence.Column
import javax.validation.constraints.NotBlank

interface UserInterface {
    val id: Long?

    val name: String

    val email: String

    val password: String

    val birth: String

    val location: String

    val profilePic: MultipartFile

    val banner: MultipartFile

    val phone: String

    val instagram: String?

    val facebook: String?

    val whatsapp: String?
}