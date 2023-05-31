package com.example.kotlindemo.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import com.example.kotlindemo.auth.JwtConfigurer


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

  @Autowired
  private lateinit var jwtTokenProvider: JwtTokenProvider

  override fun configure(http: HttpSecurity) {
    http.csrf().disable()
      .authorizeRequests()
      .antMatchers("/auth/user/login", "/api/user/**").permitAll() // Rotas públicas
      .anyRequest().authenticated() // Todas as outras rotas exigem autenticação
      .and()
      .apply(JwtConfigurer(jwtTokenProvider))
  }
}
