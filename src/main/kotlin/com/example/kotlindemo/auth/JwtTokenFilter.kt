package com.example.kotlindemo.auth


import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter(private val jwtTokenProvider: JwtTokenProvider) : OncePerRequestFilter() {

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    val token = extractToken(request)
    if (token != null && jwtTokenProvider.validateToken(token)) {
      val auth = UsernamePasswordAuthenticationToken(getUsernameFromToken(token), null, emptyList())
      SecurityContextHolder.getContext().authentication = auth
    }
    filterChain.doFilter(request, response)
  }

   fun extractToken(request: HttpServletRequest): String? {
    val bearerToken = request.getHeader("Authorization")
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7)
    }
    return null
  }

   fun getUsernameFromToken(token: String): String {
    return Jwts.parser().setSigningKey(jwtTokenProvider.jwtSecrets).parseClaimsJws(token).body.subject
  }
}
