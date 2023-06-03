package com.example.kotlindemo.dtos

import com.example.kotlindemo.model.PostPets
import com.example.kotlindemo.model.User
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreatePostPetsDTO(

  @field:NotEmpty(message = "this field can not be empty")
  val name: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val postPic: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val race: String = "",

  @field:NotNull(message = "this field can not be empty")
  val specie: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val sex: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val age: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val size: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val weight: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val about: String = "",

  @field:NotEmpty(message = "this field can not be empty")
  val petLocation: String = "",

  @field:NotNull(message = "this field can not be empty")
  val isAdopted: Boolean,

  @field:NotNull(message = "this field can not be empty")
  val isCastrated: Boolean,

  @field:NotNull(message = "this field can not be empty")
  val isVaccinated: Boolean,

  @field:NotNull(message = "this field can not be empty")
  val isPedigree: Boolean,

  @field:NotNull(message = "this field can not be empty")
  val isDewormed: Boolean,

  @field:NotNull(message = "this field can not be empty")
  val isEspecialNeeds: Boolean,



  var userId: Long = 0
) {
  fun toEntity(): PostPets = PostPets(
    name = this.name,
    postPic = this.postPic,
    race = this.race,
    specie = this.specie,
    sex = this.sex,
    age = this.age,
    size = this.size,
    weight = this.weight,
    about = this.about,
    petLocation = this.petLocation,
    isAdopted = this.isAdopted,
    isCastrated = this.isCastrated,
    isVaccinated = this.isVaccinated,
    isDewormed = this.isDewormed,
    isEspecialNeeds = this.isEspecialNeeds,
    isPedigree = this.isPedigree,
    user = User(
      id = this.userId
    )
  )
}
