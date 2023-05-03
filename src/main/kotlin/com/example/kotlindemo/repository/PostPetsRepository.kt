package com.example.kotlindemo.repository

import com.example.kotlindemo.model.PostPets
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostPetsRepository: JpaRepository<PostPets, Long>