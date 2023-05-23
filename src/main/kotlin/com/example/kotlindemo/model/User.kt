package com.example.kotlindemo.model

import org.springframework.lang.Nullable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity(name = "users")
data class User (
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @get: NotBlank
    val name: String = "",

    @get: NotBlank
    val email: String = "",

    @get: NotBlank
    var password: String = "",

    @get: NotBlank
    val birth: String = "",

    @get: NotBlank
    val location: String = "",

    @Column(name = "profile_pic")
    val profilePic: String = "",

    @get: NotBlank
    val banner: String = "",

    @get: NotBlank
    val phone: String = "",

    @Nullable
    val instagram: String? = "",

    @Nullable
    val facebook: String? = "",

    @Nullable
    val whatsapp: String? = "",

) {
    @PrePersist
    @PreUpdate
    fun hashPassword() {
        val encoder = BCryptPasswordEncoder()
        password = encoder.encode(password)
    }
}