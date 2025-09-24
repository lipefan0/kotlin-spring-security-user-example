package br.com.contis.kotlin_spring_security_user_example.controller

import br.com.contis.kotlin_spring_security_user_example.config.JwtTokenProvider
import br.com.contis.kotlin_spring_security_user_example.dtos.LoginRequest
import br.com.contis.kotlin_spring_security_user_example.dtos.RegisterDTO
import br.com.contis.kotlin_spring_security_user_example.dtos.TokenResponse
import br.com.contis.kotlin_spring_security_user_example.entity.UserEntity
import br.com.contis.kotlin_spring_security_user_example.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwt: JwtTokenProvider,
    private val userService: UserService
) {
    @PostMapping("/register")
    fun register(
        @RequestBody body: RegisterDTO
    ): ResponseEntity<UserEntity> {

        val user = UserEntity(
            name = body.name,
            email = body.email,
            passwordHash = body.password,
            role = body.role
        )
        return ResponseEntity.ok(userService.save(user))
    }

    @PostMapping("/login")
    fun login(
        @RequestBody body: LoginRequest
    ): ResponseEntity<TokenResponse> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(body.email, body.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val user = authentication.principal as UserEntity
        val token = jwt.generateToken(user)
        return ResponseEntity.ok(TokenResponse(token))
    }

}