package br.com.contis.kotlin_spring_security_user_example.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "tb_users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false)
    val name: String,
    @Column(unique = true, nullable = false)
    val email: String,
    val passwordHash: String,
    @Enumerated(EnumType.STRING)
    val role: RoleEnum
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return listOf(SimpleGrantedAuthority("ROLE_${role.name}"))
    }

    override fun getPassword(): String = passwordHash

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}
