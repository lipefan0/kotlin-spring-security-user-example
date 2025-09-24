package br.com.contis.kotlin_spring_security_user_example.dtos

import br.com.contis.kotlin_spring_security_user_example.entity.RoleEnum

data class RegisterDTO(
    val name: String,
    val email: String,
    val password: String,
    val role: RoleEnum = RoleEnum.USER
)