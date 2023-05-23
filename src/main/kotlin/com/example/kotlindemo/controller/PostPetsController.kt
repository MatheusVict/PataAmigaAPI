package com.example.kotlindemo.controller

import com.example.kotlindemo.model.PostPets
import com.example.kotlindemo.repository.PostPetsRepository
import com.example.kotlindemo.services.PostPetsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PostPetsController(private val postPetsService: PostPetsService) {
    @GetMapping("/postsPets")
    fun getAllPostPets(): List<PostPets> =
        postPetsService.getAllPostPets()

    @GetMapping("/postsPets/{id}")
    fun getPostPetsId(@PathVariable(value = "id") postPetsId: Long): ResponseEntity<PostPets> =
         postPetsService.getPostPetsId(postPetsId)

     @GetMapping("/postsPets/users/{id}")
    fun getPostPetsForId(@PathVariable(value = "id") userId: Long): ResponseEntity<List<PostPets>> =
        postPetsService.getPostsByUser(userId)

    @PostMapping("/postsPets")
    fun createNewPostPets(@Valid @RequestBody postPets: PostPets): ResponseEntity<PostPets> =
        postPetsService.createNewPostPets(postPets)



    @PutMapping("/postsPets/{id}")
    fun updatePostPetsId(@PathVariable(value = "id") postPetsId: Long,
                     @Valid @RequestBody newPostPets: PostPets): ResponseEntity<PostPets> =
        postPetsService.updatePostPetsId(postPetsId, newPostPets)

    @DeleteMapping("/postsPets/{id}")
    fun deletePostPetsId(@PathVariable(value = "id") postPetsId: Long): ResponseEntity<Void> =
        postPetsService.deletePostPetsId(postPetsId)

}