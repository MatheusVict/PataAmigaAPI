package com.example.kotlindemo.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.security.SecureRandom
import java.util.Arrays
import java.util.Date
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

@Component
class JwtTokenProvider {

  // Chave secreta para assinar e verificar o JWT

   //var jwtSecret: String = "MinhaChaveSecretaDoJWT"

  // Tempo de validade do JWT
  private var jwtExpiration: Long = 86400000

  fun generateRandomKey(): SecretKey {
    val random = SecureRandom()
    val keyGenerator = KeyGenerator.getInstance("HmacSHA512")
    keyGenerator.init(random)
    return keyGenerator.generateKey()
  }


  val jwtSecrets = generateRandomKey()


  fun generateToken(username: String): String {
    val now = Date()
    val expiryDate = Date(now.time + jwtExpiration)

    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .signWith(SignatureAlgorithm.HS512, jwtSecrets)
      .compact()
  }

  fun validateToken(token: String): Boolean {
    try {
      Jwts.parser().setSigningKey(jwtSecrets).parseClaimsJws(token)
      return true
    } catch (ex: Exception) {
      return false
    }
  }
}
