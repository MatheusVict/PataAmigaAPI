package com.example.kotlindemo.controller


import com.example.kotlindemo.auth.JwtTokenProvider
import com.example.kotlindemo.dtos.LoginDTO
import com.example.kotlindemo.dtos.ReturnTokenDTO
import com.example.kotlindemo.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/auth")
class AuthController(private val userService: UserService, private val jwtTokenProvider: JwtTokenProvider) {

  @PostMapping("/user/login")
  fun login(@Valid @RequestBody body: LoginDTO): Any {
    val user = this.userService.getUserByEmail(body.email) ?: return ResponseEntity.badRequest().body("User notFound")

    if (!user.comparePassword(body.password)) {
      return ResponseEntity.badRequest().body("Invalid credentials")
    }

    val issuer = user.id.toString()

    val token = jwtTokenProvider.generateToken(issuer)

    return ResponseEntity.ok(ReturnTokenDTO(token))

  }

}