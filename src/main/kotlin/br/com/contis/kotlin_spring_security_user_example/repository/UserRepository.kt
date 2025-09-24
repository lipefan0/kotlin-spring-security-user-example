package br.com.contis.kotlin_spring_security_user_example.repository

import br.com.contis.kotlin_spring_security_user_example.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): Optional<UserEntity>
}