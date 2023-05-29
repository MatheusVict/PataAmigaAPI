package com.example.kotlindemo.controller


import com.example.kotlindemo.dtos.ChangePasswordDTO
import com.example.kotlindemo.dtos.CreateUserDTO
import com.example.kotlindemo.dtos.UpdateUserDTO
import com.example.kotlindemo.dtos.UserReturnDTO
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
  fun getAllUser(): List<UserReturnDTO> =
    this.userService.getAllUser()

  @PostMapping("/user")
  fun createNewUser(@Valid @RequestBody user: CreateUserDTO): ResponseEntity<Any> {
    return this.userService.createNewUser(user.toEntity())
  }

  @GetMapping("/user/{id}")
  fun getUserById(@PathVariable(value = "id") userId: Long): ResponseEntity<UserReturnDTO> {
    return ResponseEntity.ok().body(UserReturnDTO(this.userService.getUserById(userId)))
  }


  @PutMapping("/user/{id}")
  fun updateUserId(
    @PathVariable(value = "id") userId: Long,
    @Valid @RequestBody newUser: UpdateUserDTO
  ): ResponseEntity<UserReturnDTO> {

    return ResponseEntity.ok().body(this.userService.updateUserId(userId, newUser))
  }

  @PatchMapping("/postsPets/change_password/{id}")
  fun changeUserPassword(@Valid @RequestBody body: ChangePasswordDTO) {
    ResponseEntity.ok(this.userService.changePassword(body.email, body.newPassword))
  }

  @DeleteMapping("/user/{id}")
  fun deleteUserId(@PathVariable(value = "id") userId: Long): ResponseEntity<Void> {
    return this.userService.deleteUserId(userId)
  }
}