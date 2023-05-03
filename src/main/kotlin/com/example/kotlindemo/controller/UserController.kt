package com.example.kotlindemo.controller


import com.example.kotlindemo.model.User
import com.example.kotlindemo.repository.UserRepository
import org.mindrot.jbcrypt.BCrypt
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/user")
    fun getAllArticles(): List<User> =
        userRepository.findAll()

    @PostMapping("/user")
    fun createNewArticle(@Valid @RequestBody user: User): User {
        val encodedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        user.password = encodedPassword
        return userRepository.save(user)
    }

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<User> {
        return userRepository.findById(userId).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/user/{id}")
    fun updateUserId(@PathVariable(value = "id") userId: Long,
                          @Valid @RequestBody newUser: User): ResponseEntity<User> {

        return userRepository.findById(userId).map { existingUser ->
            val updatedUser: User = existingUser
                    .copy(
                        email = newUser.email,
                        password = newUser.password,
                        name = newUser.name,
                        banner = newUser.banner,
                        birth = newUser.birth,
                        facebook = newUser.facebook,
                        instagram = newUser.instagram,
                        location = newUser.location,
                        phone = newUser.phone,
                        profilePic = newUser.profilePic,
                        whatsapp = newUser.whatsapp
                    )

            ResponseEntity.ok().body(userRepository.save(updatedUser))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/user/{id}")
    fun deleteUserId(@PathVariable(value = "id") userId: Long): ResponseEntity<Void> {

        return userRepository.findById(userId).map { user  ->
            userRepository.delete(user)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}