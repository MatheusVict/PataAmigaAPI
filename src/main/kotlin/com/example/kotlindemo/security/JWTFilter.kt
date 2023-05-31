package com.example.kotlindemo.security
/*
import com.example.kotlindemo.security.JWTCreator.create
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureException
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTFilter : OncePerRequestFilter() {
  @Throws(ServletException::class, IOException::class)
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    //obtem o token da request com AUTHORIZATION
    val token = request.getHeader(JWTCreator.HEADER_AUTHORIZATION)
    //esta implementação só esta validando a integridade do token
    try {
      if (token != null && !token.isEmpty()) {
        val tokenObject = create(token, SecurityConfig.PREFIX, SecurityConfig.KEY)
        val userToken = UsernamePasswordAuthenticationToken(
          tokenObject.subject,
          null,
        )
        SecurityContextHolder.getContext().authentication = userToken
      } else {
        SecurityContextHolder.clearContext()
      }
      filterChain.doFilter(request, response)
    } catch (e: ExpiredJwtException) {
      e.printStackTrace()
      response.status = HttpStatus.FORBIDDEN.value()
      return
    } catch (e: UnsupportedJwtException) {
      e.printStackTrace()
      response.status = HttpStatus.FORBIDDEN.value()
      return
    } catch (e: MalformedJwtException) {
      e.printStackTrace()
      response.status = HttpStatus.FORBIDDEN.value()
      return
    } catch (e: SignatureException) {
      e.printStackTrace()
      response.status = HttpStatus.FORBIDDEN.value()
      return
    }
  }
}*/