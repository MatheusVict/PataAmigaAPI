package com.example.kotlindemo.controller


import com.example.kotlindemo.model.User
import com.example.kotlindemo.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService) {

    @GetMapping("/user")
    fun getAllUser(): List<User> =
        this.userService.getAllUser()

    @PostMapping("/user")
    fun createNewUser(@Valid @RequestBody user: User): ResponseEntity<User> {
       return ResponseEntity.ok(this.userService.createNewUser(user))
    }

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<User> {
        return this.userService.getUserById(userId)
    }

    @PutMapping("/user/{id}")
    fun updateUserId(@PathVariable(value = "id") userId: Long,
                          @Valid @RequestBody newUser: User): ResponseEntity<User> {

        return this.userService.updateUserId(userId, newUser)

    }

    @DeleteMapping("/user/{id}")
    fun deleteUserId(@PathVariable(value = "id") userId: Long): ResponseEntity<Void> {
        return this.userService.deleteUserId(userId)
    }
}