package com.example.kotlindemo.services

import com.example.kotlindemo.dtos.UserReturnDTO
import com.example.kotlindemo.model.User
import com.example.kotlindemo.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUser(): List<UserReturnDTO> =
        this.userRepository.findAll().map { user ->
            mapUserDTO(user)
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
           return ResponseEntity.status(201).body(mapUserDTO(createdUser))
        }
    }

    fun getUserById(userId: Long): ResponseEntity<User> {
        return this.userRepository.findById(userId).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun updateUserId(userId: Long, newUser: User): ResponseEntity<UserReturnDTO>? {

        /*if (!isEmailValid(newUser.email)) {
            return ResponseEntity.badRequest().body("Invalid email")
        }*/

        return this.userRepository.findById(userId).map { existingUser ->
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

            ResponseEntity.ok().body(mapUserDTO(this.userRepository.save(updatedUser)))
        }.orElse(ResponseEntity.notFound().build())

    }

    fun deleteUserId(userId: Long): ResponseEntity<Void> {

        return this.userRepository.findById(userId).map { user  ->
            this.userRepository.delete(user)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }

    private fun mapUserDTO(user: User): UserReturnDTO {
        return UserReturnDTO(
            name = user.name,
            email = user.email,
            facebook = user.facebook,
            instagram = user.instagram,
            banner = user.banner,
            location = user.location,
            whatsapp = user.whatsapp,
            phone = user.phone,
            profilePic = user.profilePic,
            birth = user.birth,
            id = user.id
        )
    }
    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        val pattern = Pattern.compile(emailPattern)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

}