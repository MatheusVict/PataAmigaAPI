package com.example.kotlindemo.services

import com.example.kotlindemo.model.LikedPostPets
import com.example.kotlindemo.model.User
import com.example.kotlindemo.repository.LikedPostPetsRepository
import org.springframework.stereotype.Service

@Service
class LikedPostPetsServices(private val likedPostPetsRepository: LikedPostPetsRepository){
    fun likePosts(likedPostPets: LikedPostPets): LikedPostPets =
        this.likedPostPetsRepository.save(likedPostPets)
    fun findAllPostFromUser(idUser: Long):List<LikedPostPets>?{
        return this.likedPostPetsRepository.findPostByIdUser(idUser)
    }
    fun removeLikedPostPets(idLikedPostPets: Long){
        this.likedPostPetsRepository.deleteById(idLikedPostPets)
    }
}
