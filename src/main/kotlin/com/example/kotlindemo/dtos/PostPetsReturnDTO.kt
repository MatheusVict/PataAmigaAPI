package com.example.kotlindemo.dtos

import com.example.kotlindemo.model.PostPets
import com.example.kotlindemo.model.User
data class PostPetsReturnDTO(
  val id: Long = 0,

  val postPic: String = "",

  val name: String = "",

  val race: String = "",

  val sex: String = "",

  val age: String = "",

  val size: String = "",

  val weight: String = "",

  val about: String = "",

  val petLocation: String = "",

  val isAdopted: Boolean,

  val isCastrated: Boolean,

  val isVaccinated: Boolean,

  val isPedigree: Boolean,

  val isDewormed: Boolean,

  val isEspecialNeeds: Boolean,

  val userId: Long,

  val userPic: String?,

  val userName: String,

  val userLocation: String
) {
  constructor(postPets: PostPets): this (
    id = postPets.id,
    postPic = postPets.postPic,
    name = postPets.name,
    race = postPets.race,
    sex = postPets.sex,
    age = postPets.age,
    size = postPets.size,
    weight = postPets.weight,
    about = postPets.about,
    isAdopted = postPets.isAdopted,
    isCastrated = postPets.isCastrated,
    isVaccinated = postPets.isVaccinated,
    isPedigree = postPets.isPedigree,
    isDewormed = postPets.isDewormed,
    isEspecialNeeds = postPets.isEspecialNeeds,
    userId = postPets.user.id,
    userPic = postPets.user.profilePic,
    userName = postPets.user.name,
    userLocation = postPets.user.location,
    petLocation = postPets.petLocation
  )
}
