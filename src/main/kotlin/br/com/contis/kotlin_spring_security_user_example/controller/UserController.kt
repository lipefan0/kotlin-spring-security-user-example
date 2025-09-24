package br.com.contis.kotlin_spring_security_user_example.controller

import br.com.contis.kotlin_spring_security_user_example.entity.UserEntity
import br.com.contis.kotlin_spring_security_user_example.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getAllUsers(): List<UserEntity> = userService.findAll()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserEntity> {
        return ResponseEntity.ok(userService.findUserById(id))
    }
}