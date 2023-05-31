package com.example.kotlindemo.controller


import com.example.kotlindemo.auth.JwtTokenFilter
import com.example.kotlindemo.auth.JwtTokenProvider
import com.example.kotlindemo.dtos.ChangePasswordDTO
import com.example.kotlindemo.dtos.CreateUserDTO
import com.example.kotlindemo.dtos.UpdateUserDTO
import com.example.kotlindemo.dtos.UserReturnDTO
import com.example.kotlindemo.model.User
import com.example.kotlindemo.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService, private val jwtTokenProvider: JwtTokenProvider) {

  @GetMapping("/user")
  fun getAllUser(): List<UserReturnDTO> =
    this.userService.getAllUser()

  @PostMapping("/user")
  fun createNewUser(@Valid @RequestBody user: CreateUserDTO): ResponseEntity<Any> {
    return this.userService.createNewUser(user.toEntity())
  }

  @GetMapping("/user/byself")
  fun getUserById(/*@PathVariable(value = "id") userId: Long,*/ request: HttpServletRequest): ResponseEntity<Any> {
    val jwtTokenFilter = JwtTokenFilter(jwtTokenProvider)
    val token = jwtTokenFilter.extractToken(request)
    val isValid = jwtTokenProvider.validateToken(token.toString())
    println(" token: $token é valido: $isValid")
    return if (isValid) {
      val userToken = jwtTokenFilter.getUsernameFromToken(token.toString())
      println(" o User é $userToken")
       ResponseEntity.ok().body(UserReturnDTO(this.userService.getUserById(userToken.toLong())))
    } else ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED")
  }


  @PutMapping("/user")
  fun updateUserId(
    /*@PathVariable(value = "id") userId: Long,*/
    @Valid @RequestBody newUser: UpdateUserDTO,
    request: HttpServletRequest
  ): ResponseEntity<Any> {
    val jwtTokenFilter = JwtTokenFilter(jwtTokenProvider)
    val token = jwtTokenFilter.extractToken(request)
    val isValid = jwtTokenProvider.validateToken(token.toString())
    println(" token: $token é valido: $isValid")
    return if (isValid) {
      val userToken = jwtTokenFilter.getUsernameFromToken(token.toString())
      println(" token: $token é valido: $isValid")
      ResponseEntity.ok().body(this.userService.updateUserId(userToken.toLong() , newUser))
    } else ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED")
  }

  @PatchMapping("/user/change_password")
  fun changeUserPassword(@Valid @RequestBody body: ChangePasswordDTO, request: HttpServletRequest): ResponseEntity<Any> {
    val jwtTokenFilter = JwtTokenFilter(jwtTokenProvider)
    val token = jwtTokenFilter.extractToken(request)
    val isValid = jwtTokenProvider.validateToken(token.toString())
    println(" token: $token é valido: $isValid")
    return if (isValid) {
      val userToken = jwtTokenFilter.getUsernameFromToken(token.toString())
      println(" token: $token é valido: $isValid")
      ResponseEntity.ok(this.userService.changePassword(body.email, body.newPassword))
    } else ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED")
  }

  @DeleteMapping("/user")
  fun deleteUserId(/*@PathVariable(value = "id") userId: Long,*/ request: HttpServletRequest): ResponseEntity<Any> {
    val jwtTokenFilter = JwtTokenFilter(jwtTokenProvider)
    val token = jwtTokenFilter.extractToken(request)
    val isValid = jwtTokenProvider.validateToken(token.toString())
    println(" token: $token é valido: $isValid")
    return if (isValid) {
      val userToken = jwtTokenFilter . getUsernameFromToken (token.toString())
      println(" token: $token é valido: $isValid")
      ResponseEntity.ok().body(this.userService.deleteUserId(userToken.toLong()))
    } else ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED")
  }
}
