package com.example.kotlindemo.services

import com.example.kotlindemo.model.User
import com.example.kotlindemo.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUser(): List<User> =
        userRepository.findAll()

    fun createNewUser(user: User): User {

        return userRepository.save(user)
    }

    fun getUserById(userId: Long): ResponseEntity<User> {
        return userRepository.findById(userId).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun updateUserId(userId: Long, newUser: User): ResponseEntity<User> {

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