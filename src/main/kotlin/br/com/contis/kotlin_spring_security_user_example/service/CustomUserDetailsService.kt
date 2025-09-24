package br.com.contis.kotlin_spring_security_user_example.service

import br.com.contis.kotlin_spring_security_user_example.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        return userRepository.findByEmail(username)
            .orElseThrow { UsernameNotFoundException("Usuario não encontrado com o email: $username") }

    }
}