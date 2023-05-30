package com.example.kotlindemo.repository

import com.example.kotlindemo.model.LikedPostPets
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LikedPostPetsRepository: JpaRepository<LikedPostPets, Long> {

  fun findByUserId(userId: Long): List<LikedPostPets>?
}
