package com.example.kotlindemo.dtos

import com.example.kotlindemo.model.LikedPostPets
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreateLikedPostPetsDTO(
  @field:NotNull(message = "this field can not be empty")
  val userId: Long,

  @field:NotNull(message = "this field can not be empty")
  val postId: Long

) {
  /*fun toEntity(): LikedPostPets = LikedPostPets(
    userId = this.userId,
    post = this.postId
  )*/
}
