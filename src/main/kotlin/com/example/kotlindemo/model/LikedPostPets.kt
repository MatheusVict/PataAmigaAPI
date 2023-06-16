package com.example.kotlindemo.model

import javax.annotation.processing.Generated
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class LikedPostPets(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long,
        @Column
    val idUser: Long,
        @Column
    val idPostPets: Long,


)
