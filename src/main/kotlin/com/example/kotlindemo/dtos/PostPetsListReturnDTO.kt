package com.example.kotlindemo.dtos

import com.example.kotlindemo.model.PostPets

data class PostPetsListReturnDTO(
  val id: Long = 0,

  val postPic: String = "",

  val name: String = "",

  val race: String = "",

  val specie: String = "",

  val sex: String = "",

  val age: String = "",

  val petLocation: String = ""
) {
  constructor(postPets: PostPets): this (
    id = postPets.id,
    postPic = postPets.postPic,
    name = postPets.name,
    race = postPets.race,
    sex = postPets.sex,
    age = postPets.age,
    petLocation = postPets.petLocation,
    specie = postPets.specie
  )
}
