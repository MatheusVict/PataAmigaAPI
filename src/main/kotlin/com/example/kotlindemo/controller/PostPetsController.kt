package com.example.kotlindemo.controller

import com.example.kotlindemo.model.PostPets
import com.example.kotlindemo.repository.PostPetsRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PostPetsController(private val postPetsRepository: PostPetsRepository) {
    @GetMapping("/post_pets")
    fun getAllPostPets(): List<PostPets> =
        postPetsRepository.findAll()

    @GetMapping("/post_pets/{id}")
    fun getPostPetsId(@PathVariable(value = "id") postPetsId: Long): ResponseEntity<PostPets> {
        return postPetsRepository.findById(postPetsId).map { postPets ->
            ResponseEntity.ok(postPets)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/post_pets")
    fun createNewPostPets(@Valid @RequestBody postPets: PostPets): PostPets {
        return postPetsRepository.save(postPets)
    }


    @PutMapping("/post_pets/{id}")
    fun updatePostPetsId(@PathVariable(value = "id") postPetsId: Long,
                     @Valid @RequestBody newPostPets: PostPets): ResponseEntity<PostPets> {

        return postPetsRepository.findById(postPetsId).map { existingPostPets ->
            val updatedPostPets: PostPets = existingPostPets
                .copy(
                    about = newPostPets.about,
                    age = newPostPets.age,
                    isAdpoted = newPostPets.isAdpoted,
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

            ResponseEntity.ok().body(postPetsRepository.save(updatedPostPets))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/post_pets/{id}")
    fun deletePostPetsId(@PathVariable(value = "id") postPetsId: Long): ResponseEntity<Void> {

        return postPetsRepository.findById(postPetsId).map { postPets  ->
            postPetsRepository.delete(postPets)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }

}