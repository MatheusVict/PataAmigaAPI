package com.example.kotlindemo.services

import com.example.kotlindemo.model.PostPets
import com.example.kotlindemo.repository.PostPetsRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Service
class PostPetsService(private val postPetsRepository: PostPetsRepository) {

    fun getAllPostPets(): List<PostPets> =
        postPetsRepository.findAll()

    fun getPostPetsId(postPetsId: Long): ResponseEntity<PostPets> {
        return postPetsRepository.findById(postPetsId).map { postPets ->
            ResponseEntity.ok(postPets)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun createNewPostPets(postPets: PostPets): PostPets {
        return postPetsRepository.save(postPets)
    }

    fun updatePostPetsId(postPetsId: Long, newPostPets: PostPets): ResponseEntity<PostPets> {

        return postPetsRepository.findById(postPetsId).map { existingPostPets ->
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

            ResponseEntity.ok().body(postPetsRepository.save(updatedPostPets))
        }.orElse(ResponseEntity.notFound().build())

    }

    fun deletePostPetsId(postPetsId: Long): ResponseEntity<Void> {

        return postPetsRepository.findById(postPetsId).map { postPets  ->
            postPetsRepository.delete(postPets)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}