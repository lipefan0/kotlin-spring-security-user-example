package br.com.contis.kotlin_spring_security_user_example.dtos

data class LoginRequest(
    val email: String,
    val password: String
)
