package com.example.kotlindemo.services

import com.example.kotlindemo.model.PostPets
import com.example.kotlindemo.model.User
import com.example.kotlindemo.repository.PostPetsRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Service
class PostPetsService(private val postPetsRepository: PostPetsRepository, private val userService: UserService) {

    fun getAllPostPets(): List<PostPets> =
        this.postPetsRepository.findAll()

    fun getPostPetsId(postPetsId: Long): ResponseEntity<PostPets> {
        return this.postPetsRepository.findById(postPetsId).map { postPets ->
            ResponseEntity.ok(postPets)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun createNewPostPets(postPets: PostPets): ResponseEntity<PostPets> {
        val userId = postPets.user.id
        val userResponse = this.userService.getUserById(userId)

        if (userResponse.statusCode.is2xxSuccessful) {
            val user: User? = userResponse.body

            if (user != null) {
                postPets.user = user
            }
            return ResponseEntity.ok(this.postPetsRepository.save(postPets))
        }
        return ResponseEntity.status(userResponse.statusCode).build()
    }

    fun getPostsByUser(userId: Long): ResponseEntity<List<PostPets>> {
        val user = User(
            id = userId
        )
        val posts = this.postPetsRepository.findByUser(user)
        posts?.let {
            return ResponseEntity.ok(it)
        } ?: run {
         return ResponseEntity.notFound().build()
        }
    }



    fun updatePostPetsId(postPetsId: Long, newPostPets: PostPets): ResponseEntity<PostPets> {

        return this.postPetsRepository.findById(postPetsId).map { existingPostPets ->
            val updatedPostPets: PostPets = existingPostPets
                .copy(
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
                    weight = newPostPets.weight
                )

            ResponseEntity.ok().body(this.postPetsRepository.save(updatedPostPets))
        }.orElse(ResponseEntity.notFound().build())

    }

    fun deletePostPetsId(postPetsId: Long): ResponseEntity<Void> {

        return this.postPetsRepository.findById(postPetsId).map { postPets  ->
           this. postPetsRepository.delete(postPets)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}