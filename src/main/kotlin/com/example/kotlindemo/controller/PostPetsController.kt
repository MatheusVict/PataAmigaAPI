package com.example.kotlindemo.controller

import com.example.kotlindemo.repository.PostPetsRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PostPetsController(private val postPetsRepository: PostPetsRepository) {
}