package com.example.kotlindemo.security
/*
import io.jsonwebtoken.*
import java.util.stream.Collectors


object JWTCreator {
  const val HEADER_AUTHORIZATION = "Authorization"
  const val ROLES_AUTHORITIES = "authorities"
  fun create(prefix: String, key: String?, jwtObject: JWTObject): String {
    val token = Jwts.builder().setSubject(jwtObject.subject).setIssuedAt(jwtObject.issuedAt)
      .setExpiration(jwtObject.expiration)
     .signWith(SignatureAlgorithm.HS512, key).compact()
    return "$prefix $token"
  }

  @Throws(
    ExpiredJwtException::class,
    UnsupportedJwtException::class,
    MalformedJwtException::class,
    SignatureException::class
  )
  fun create(token: String, prefix: String?, key: String?): JWTObject {
    var token = token
    val `object` = JWTObject()
    token = token.replace(prefix!!, "")
    val claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).body
    `object`.subject = claims.subject
    `object`.expiration = claims.expiration
    `object`.issuedAt = claims.issuedAt
    return `object`
  }

  private fun checkRoles(roles: List<String>): List<String> {
    return roles.stream().map { s: String ->
      "ROLE_" + s.replace(
        "ROLE_".toRegex(),
        ""
      )
    }.collect(Collectors.toList())
  }
}*/