package com.example.kotlindemo.model

import org.springframework.lang.Nullable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity(name = "users")
data class User (
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,

    @get: NotBlank
    var name: String = "",

    @Column(unique = true, nullable = false)
    var email: String = "",

    @get: NotBlank
    var password: String = "",

    @get: NotBlank
    var birth: String = "",

    @get: NotBlank
    var location: String = "",

    @Lob
    @Column(nullable = true,  name = "profile_pic", length = 20000, columnDefinition = "BLOB")
    var profilePic: String? = "",

    @Lob
    @Column(nullable = true, length = 20000, columnDefinition = "BLOB")
    var banner: String? = "",

    @get: NotBlank
    var phone: String = "",

    @Nullable
    var instagram: String? = "",

    @Nullable
    var facebook: String? = "",

    @Nullable
    var whatsapp: String? = "",

) {
    @PrePersist
    fun hashPassword() {
        val encoder = BCryptPasswordEncoder()
        password = encoder.encode(password)
    }

    fun comparePassword(password: String): Boolean {
        val encoder = BCryptPasswordEncoder()
        return encoder.matches(password, this.password)
    }
}