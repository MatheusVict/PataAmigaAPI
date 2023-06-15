package com.example.kotlindemo.services

import com.example.kotlindemo.utils.Email
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val javaMailSender: JavaMailSender) {
    fun sendMail(toEmail: String, subject: String, message: String) {
        val mailMessage = SimpleMailMessage()
        mailMessage.setFrom("dan.danielfs@gmail.com")
        mailMessage.setTo(toEmail)
        mailMessage.setSubject(subject)
        mailMessage.setText(message)
        javaMailSender.send(mailMessage)
    }
}