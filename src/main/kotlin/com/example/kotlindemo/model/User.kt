package com.example.kotlindemo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.mindrot.jbcrypt.BCrypt
import org.springframework.lang.Nullable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.PrePersist
import javax.persistence.PreUpdate
import javax.validation.constraints.NotBlank

@Entity
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
    val instagram: String?,

    @Nullable
    val facebook: String?,

    @Nullable
    val whatsapp: String?,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, targetEntity = PostPets::class)
    val postPets: MutableList<PostPets> = mutableListOf()
) {
    @PrePersist
    @PreUpdate
    fun hashPassword() {
        val encoder = BCryptPasswordEncoder()
        password = encoder.encode(password)
    }

    /*fun addPostPets(postPets: PostPets) {
        postPets.user = this
        this.postPets.add(postPets)
    }

    fun removePostPets(postPets: PostPets) {
        postPets.user = null
        this.postPets.remove(postPets)
    }*/
}