package com.example.kotlindemo.controller

import com.example.kotlindemo.Service.UserService
import com.example.kotlindemo.interfaces.UserInterface
import com.example.kotlindemo.model.User
import com.example.kotlindemo.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class UserController(private val userRepository: UserRepository, private val userService: UserService) {

    @GetMapping("/user")
    fun getAllArticles(): List<User> =
            userService.getAllUsers()

    @PostMapping("/user")
    fun createNewArticle(@Valid @RequestBody user: User): User = userService.createUser(user)

    @GetMapping("/user/{id}")
    fun getArticleById(@PathVariable(value = "id") articleId: Long): ResponseEntity<User> {
        return userRepository.findById(articleId).map { article ->
            ResponseEntity.ok(article)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/user/{id}")
    fun updateArticleById(@PathVariable(value = "id") articleId: Long,
                          @Valid @RequestBody newUser: User): ResponseEntity<User> {

        return userRepository.findById(articleId).map { existingUser ->
            val updatedUser: User = existingUser
                    .copy(email = newUser.email, id = newUser.id)

            ResponseEntity.ok().body(userRepository.save(updatedUser))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/user/{id}")
    fun deleteArticleById(@PathVariable(value = "id") articleId: Long): ResponseEntity<Void> {

        return userRepository.findById(articleId).map { article  ->
            userRepository.delete(article)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}