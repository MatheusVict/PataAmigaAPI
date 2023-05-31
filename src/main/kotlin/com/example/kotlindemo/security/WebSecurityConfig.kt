package com.example.kotlindemo.security
/*
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.annotation.WebServlet


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {


  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    http.headers().frameOptions().disable()
    http.cors().and().csrf().disable()
      .addFilterAfter(JWTFilter(), UsernamePasswordAuthenticationFilter::class.java)
      .authorizeRequests()
      .antMatchers(*SWAGGER_WHITELIST).permitAll()
      .antMatchers("/h2-console/**").permitAll()
      .antMatchers(HttpMethod.POST, "/auth/user/login").permitAll()
      .antMatchers(HttpMethod.POST, "/api/user").permitAll()
      .anyRequest().authenticated()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
  }

  companion object {
    private val SWAGGER_WHITELIST = arrayOf(
      "/v2/api-docs",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**"
    )
  }
}*/