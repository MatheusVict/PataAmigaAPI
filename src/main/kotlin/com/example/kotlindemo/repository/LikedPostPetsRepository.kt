package com.example.kotlindemo.repository

import com.example.kotlindemo.model.LikedPostPets
import com.example.kotlindemo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


interface LikedPostPetsRepository:JpaRepository<LikedPostPets,Long> {
    fun findPostByIdUser(idUser: Long):List<LikedPostPets>?
}