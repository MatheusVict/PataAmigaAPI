package com.example.kotlindemo.controller

import com.example.kotlindemo.auth.JwtTokenProvider
import com.example.kotlindemo.dtos.LoginDTO
/*import com.example.kotlindemo.dtos.Session
import com.example.kotlindemo.security.JWTCreator
import com.example.kotlindemo.security.JWTObject
import com.example.kotlindemo.security.SecurityConfig*/
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

    /*val session = Session()
    session.login = user.email

    val jwtObject = JWTObject()
    jwtObject.issuedAt =  Date(System.currentTimeMillis())
    jwtObject.expiration = Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION?.toLong()!!)
    jwtObject.email = user.email
    session.token = JWTCreator.create(SecurityConfig.PREFIX.toString(), SecurityConfig.KEY, jwtObject)
    return session*/

    /* val jwt = Jwts.builder()
      .setIssuer(issuer)
      .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 100))
      .signWith(SignatureAlgorithm.HS256, "ZxjtKk7pBKG4s0/le8uOZiLapoKdE/u+37taFrgfdasfdasHW1")
      .compact()*/

    val token = jwtTokenProvider.generateToken(issuer)

    return ResponseEntity.ok(token)

  }

}