package com.example.kotlindemo.Service

import com.example.kotlindemo.model.User
import com.example.kotlindemo.repository.UserRepository
import org.mindrot.jbcrypt.BCrypt
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): List<User> = userRepository.findAll()

    fun createUser(user: User): User  {
        val encodedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        user.password = encodedPassword
        return userRepository.save(user)
    }

}