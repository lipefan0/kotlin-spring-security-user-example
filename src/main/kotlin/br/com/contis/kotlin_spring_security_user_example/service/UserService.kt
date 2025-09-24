package br.com.contis.kotlin_spring_security_user_example.service

import br.com.contis.kotlin_spring_security_user_example.entity.UserEntity
import br.com.contis.kotlin_spring_security_user_example.repository.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun save(user: UserEntity): UserEntity {
        if (userRepository.findByEmail(user.email).isPresent)
            return throw Exception("Email já cadastrado")

        val userEntity = user.copy(
            passwordHash = passwordEncoder.encode(user.password)
        )

        return userRepository.save(userEntity)
    }

    fun findAll(): List<UserEntity> = userRepository.findAll()

    fun findUserById(id: Long): UserEntity? = userRepository.findById(id)
        .orElseThrow { ChangeSetPersister.NotFoundException()}
}