package com.example.kotlindemo.controller

import com.example.kotlindemo.auth.JwtTokenFilter
import com.example.kotlindemo.auth.JwtTokenProvider
import com.example.kotlindemo.dtos.*
import com.example.kotlindemo.model.PostPets
import com.example.kotlindemo.services.PostPetsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PostPetsController(
  private val postPetsService: PostPetsService,
  private val jwtTokenProvider: JwtTokenProvider
) {
  @GetMapping("/postsPets")
  fun getAllPostPets(request: HttpServletRequest): ResponseEntity<List<PostPetsListReturnDTO>> =
    ResponseEntity.ok().body(postPetsService.getAllPostPets().map { pets ->
      PostPetsListReturnDTO(pets)
    }.toList())

  @GetMapping("/postsPets/{id}")
  fun getPostPetsId(@PathVariable(value = "id") postPetsId: Long): ResponseEntity<PostPetsReturnDTO> =
    ResponseEntity.ok().body(PostPetsReturnDTO(postPetsService.getPostPetsId(postPetsId)))

  @GetMapping("/postsPets/users")
  fun getPostPetsForUserId(request: HttpServletRequest): ResponseEntity<Any> {
    val jwtTokenFilter = JwtTokenFilter(jwtTokenProvider)
    val token = jwtTokenFilter.extractToken(request)
    val isValid = jwtTokenProvider.validateToken(token.toString())
    println(" token: $token é valido: $isValid")
    return if (isValid) {
      val userToken = jwtTokenFilter.getUsernameFromToken(token.toString())
      println(" o User é $userToken")
      ResponseEntity.ok()
        .body(postPetsService.getPostsByUser(userToken.toLong()).map { pets ->
          PostPetsListReturnDTO(pets)
        }.toList())
    } else ResponseEntity.status(HttpStatus.UNAUTHORIZED)
      .body("UNAUTHORIZED")
  }

  @PostMapping("/postsPets")
  fun createNewPostPets(@Valid @RequestBody createPostPetsDTO: CreatePostPetsDTO, request: HttpServletRequest): ResponseEntity<Any> {
    val jwtTokenFilter = JwtTokenFilter(jwtTokenProvider)
    val token = jwtTokenFilter.extractToken(request)
    val isValid = jwtTokenProvider.validateToken(token.toString())
    println(" token: $token é valido: $isValid")
    return if (isValid) {
      val userToken = jwtTokenFilter . getUsernameFromToken (token.toString())
      println(" token: $token é valido: $isValid")
      createPostPetsDTO.userId = userToken.toLong()
      ResponseEntity.status(HttpStatus.CREATED)
        .body(PostPetsReturnDTO(postPetsService.createNewPostPets(createPostPetsDTO.toEntity())))
    } else ResponseEntity.status(HttpStatus.UNAUTHORIZED)
      .body("UNAUTHORIZED")
  }


  @PutMapping("/postsPets/{id}")
  fun updatePostPetsId(
    @PathVariable(value = "id") postPetsId: Long,
    @Valid @RequestBody newPostPets: UpdatePostPetsDTO,
    request: HttpServletRequest
  ): ResponseEntity<Any> {
    val jwtTokenFilter = JwtTokenFilter(jwtTokenProvider)
    val token = jwtTokenFilter.extractToken(request)
    val isValid = jwtTokenProvider.validateToken(token.toString())
    println(" token: $token é valido: $isValid")
    return if (isValid) {
      val userToken = jwtTokenFilter.getUsernameFromToken (token.toString())
      println(" token: $token é valido: $isValid")
      newPostPets.userId = userToken.toLong()
      ResponseEntity.status(HttpStatus.CREATED)
        .body(this.postPetsService.updatePostPetsId(postPetsId, newPostPets.toEntity()))
    } else ResponseEntity.status(HttpStatus.UNAUTHORIZED)
      .body("UNAUTHORIZED")
  }

  @DeleteMapping("/postsPets/{id}")
  fun deletePostPetsId(@PathVariable(value = "id") postPetsId: Long): ResponseEntity<Void> =
    postPetsService.deletePostPetsId(postPetsId)

}