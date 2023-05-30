package com.example.kotlindemo.controller

import com.example.kotlindemo.model.LikedPostPets
import com.example.kotlindemo.services.LikedPostPetsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/likedPosts")
class LikedPostPets(private val likedPostPetsService: LikedPostPetsService) {

  @GetMapping("/{userId}")
  fun findAllLikedPostForUser(@PathVariable userId: Long): ResponseEntity<List<LikedPostPets>?> {
    return ResponseEntity.ok().body(likedPostPetsService.findAllLikedPostForUser(userId))
  }

  @PostMapping
  fun likeAPost(@Valid @RequestBody likedPostPets: LikedPostPets): ResponseEntity<LikedPostPets> =
    ResponseEntity.ok().body(this.likedPostPetsService.likeAPost(likedPostPets))
}