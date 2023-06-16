package com.example.kotlindemo.controller

import com.example.kotlindemo.model.LikedPostPets
import com.example.kotlindemo.services.LikedPostPetsServices
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/likedpost")
class LikedPostPetsController (private val likedPostPetsServices: LikedPostPetsServices){
    @PostMapping
    fun likePosts(@RequestBody likedPostPets: LikedPostPets):LikedPostPets{
        return this.likedPostPetsServices.likePosts(likedPostPets)
    }
    @GetMapping("/{id}")
    fun findAllPostFromUser(@PathVariable idUser: Long):List<LikedPostPets>?{
        return this.likedPostPetsServices.findAllPostFromUser(idUser)
    }
    @DeleteMapping("/{id}")
    fun removeLikedPostPets(@PathVariable idLikedPostPets: Long){
        this.likedPostPetsServices.removeLikedPostPets(idLikedPostPets)
    }
}