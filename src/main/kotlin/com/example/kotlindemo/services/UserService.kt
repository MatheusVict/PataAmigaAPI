package com.example.kotlindemo.services

import UserNotFoundException
import com.example.kotlindemo.dtos.UpdateUserDTO
import com.example.kotlindemo.dtos.UserReturnDTO
import com.example.kotlindemo.model.User
import com.example.kotlindemo.repository.UserRepository
import javassist.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUser(): List<UserReturnDTO> =
        this.userRepository.findAll().map { user ->
            UserReturnDTO(user)
        }.toList()

    fun createNewUser(user: User): ResponseEntity<Any> {
        val existingUser = this.userRepository.findByEmail(user.email)

        return existingUser?.let {
           return ResponseEntity.badRequest().body("User email already exists")
        } ?: run {
            if (!isEmailValid(user.email)) {
                return ResponseEntity.badRequest().body("Invalid email")
            }
            val createdUser = this.userRepository.save(user)
           return ResponseEntity.status(201).body(UserReturnDTO(createdUser))
        }
    }

    fun getUserById(userId: Long): User {
        return this.userRepository.findById(userId).orElseThrow {
            throw UserNotFoundException("ID $userId Not Found")
        }
    }

    fun updateUserId(userId: Long, updateUserDTO: UpdateUserDTO): UserReturnDTO {

        val user: User = this.getUserById(userId)
        val userToUpdate = updateUserDTO.toEntity(user)
        val userUpdated: User = this.userRepository.save(userToUpdate)
        return UserReturnDTO(userUpdated)

    }

    fun changePassword(email: String, password: String) {
        val user = userRepository.findByEmail(email) ?: throw UserNotFoundException("User Not Found")
        user.password = password
        userRepository.save(user)
    }

    fun deleteUserId(userId: Long): ResponseEntity<Void> {

        return this.userRepository.findById(userId).map { user  ->
            this.userRepository.delete(user)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        val pattern = Pattern.compile(emailPattern)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

}