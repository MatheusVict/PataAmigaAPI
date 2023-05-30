package com.example.kotlindemo.services

import com.example.kotlindemo.model.LikedPostPets
import com.example.kotlindemo.repository.LikedPostPetsRepository
import org.springframework.stereotype.Service

@Service
class LikedPostPetsService(private val likedPostPetsRepository: LikedPostPetsRepository) {

  fun likeAPost(likedPostPets: LikedPostPets): LikedPostPets {
    try {
      return this.likedPostPetsRepository.save(likedPostPets)
    } catch (e: Exception) {
      throw IllegalArgumentException("Error: $e")
    }
  }

  fun findAllLikedPostForUser(userId: Long): List<LikedPostPets>? {
    return this.likedPostPetsRepository.findByUserId(userId)
  }

}