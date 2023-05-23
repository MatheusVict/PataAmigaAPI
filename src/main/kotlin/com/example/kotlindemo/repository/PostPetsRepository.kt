package com.example.kotlindemo.repository

import com.example.kotlindemo.model.PostPets
import com.example.kotlindemo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostPetsRepository: JpaRepository<PostPets, Long> {
    fun findByUser(user: User): List<PostPets>?
}