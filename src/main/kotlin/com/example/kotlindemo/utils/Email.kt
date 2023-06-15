package com.example.kotlindemo.utils

data class Email(
        val to: String,
        val subject: String,
        val text: String,
        val withAttachment: Boolean
)