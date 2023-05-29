package com.example.kotlindemo.controller

import com.example.kotlindemo.dtos.CreatePostPetsDTO
import com.example.kotlindemo.dtos.PostPetsReturnDTO
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
  fun getPostPetsId(@PathVariable(value = "id") postPetsId: Long): ResponseEntity<PostPetsReturnDTO> =
    ResponseEntity.ok().body(PostPetsReturnDTO(postPetsService.getPostPetsId(postPetsId)))

  @GetMapping("/postsPets/users/{id}")
  fun getPostPetsForId(@PathVariable(value = "id") userId: Long): ResponseEntity<List<PostPets>> =
    ResponseEntity.ok().body(postPetsService.getPostsByUser(userId))

  @PostMapping("/postsPets")
  fun createNewPostPets(@Valid @RequestBody createPostPetsDTO: CreatePostPetsDTO): ResponseEntity<PostPetsReturnDTO> =
    ResponseEntity.status(HttpStatus.CREATED)
      .body(PostPetsReturnDTO(postPetsService.createNewPostPets(createPostPetsDTO.toEntity())))


  @PutMapping("/postsPets/{id}")
  fun updatePostPetsId(
    @PathVariable(value = "id") postPetsId: Long,
    @Valid @RequestBody newPostPets: PostPets
  ): ResponseEntity<PostPets> =
    postPetsService.updatePostPetsId(postPetsId, newPostPets)

  @DeleteMapping("/postsPets/{id}")
  fun deletePostPetsId(@PathVariable(value = "id") postPetsId: Long): ResponseEntity<Void> =
    postPetsService.deletePostPetsId(postPetsId)

}