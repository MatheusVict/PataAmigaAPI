package com.example.kotlindemo.auth

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtConfigurer(private val jwtTokenProvider: JwtTokenProvider) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

  override fun configure(http: HttpSecurity) {
    val customFilter = JwtTokenFilter(jwtTokenProvider)
    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter::class.java)
  }
}
