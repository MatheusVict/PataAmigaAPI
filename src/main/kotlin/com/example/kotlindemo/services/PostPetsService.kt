package com.example.kotlindemo.services

import UserNotFoundException
import com.example.kotlindemo.model.PostPets
import com.example.kotlindemo.model.User
import com.example.kotlindemo.repository.PostPetsRepository
import javassist.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Service
class PostPetsService(private val postPetsRepository: PostPetsRepository, private val userService: UserService) {

  fun getAllPostPets(): List<PostPets> =
    this.postPetsRepository.findAll()

  fun getPostPetsId(postPetsId: Long): PostPets {
    return this.postPetsRepository.findById(postPetsId).orElseThrow {
      throw UserNotFoundException("Post $postPetsId Not Found")
    }
  }

  fun createNewPostPets(postPets: PostPets): PostPets {
    val userId = postPets.user.id
    val user: User? = this.userService.getUserById(userId)

    return if (user != null) {
      postPets.user = user
      this.postPetsRepository.save(postPets)
    } else {
      throw IllegalArgumentException("User not found")
    }
  }

  fun getPostsByUser(userId: Long): List<PostPets> {
    val user = User(id = userId)

    return postPetsRepository.findByUser(user) ?: throw UserNotFoundException("User Not Found")
  }

  fun updatePostPetsId(postPetsId: Long, newPostPets: PostPets): ResponseEntity<PostPets> {
    this.userService.getUserById(newPostPets.user.id)

    return this.postPetsRepository.findById(postPetsId).map { existingPostPets ->
        if (existingPostPets.user.id == newPostPets.user.id) {
          val updatedPostPets: PostPets = existingPostPets.copy(
            about = newPostPets.about,
            age = newPostPets.age,
            isAdopted = newPostPets.isAdopted,
            isCastrated = newPostPets.isCastrated,
            isDewormed = newPostPets.isDewormed,
            isEspecialNeeds = newPostPets.isEspecialNeeds,
            isPedigree = newPostPets.isPedigree,
            isVaccinated = newPostPets.isVaccinated,
            name = newPostPets.name,
            race = newPostPets.race,
            sex = newPostPets.sex,
            size = newPostPets.size,
            weight = newPostPets.weight,
            postPic = newPostPets.postPic
          )
          ResponseEntity.ok().body(this.postPetsRepository.save(updatedPostPets))
        } else {
          ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
      }.orElse(ResponseEntity.notFound().build())
  }


  fun deletePostPetsId(postPetsId: Long, userId: Long): ResponseEntity<*> {
    return this.postPetsRepository.findById(postPetsId).map { postPets ->
      if (postPets.user.id == userId) {
        this.postPetsRepository.delete(postPets)
        ResponseEntity.status(HttpStatus.OK).body("")
      } else {
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You can't delete other user's posts")
      }
    }.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND"))
  }
}