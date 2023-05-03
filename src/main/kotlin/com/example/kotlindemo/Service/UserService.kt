package com.example.kotlindemo.Service

import com.example.kotlindemo.interfaces.UserInterface
import com.example.kotlindemo.model.User
import com.example.kotlindemo.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): List<User> = userRepository.findAll()

    fun createUser(user: User): User = userRepository.save(user)

}