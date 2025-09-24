package br.com.contis.kotlin_spring_security_user_example.config

import br.com.contis.kotlin_spring_security_user_example.entity.UserEntity
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Base64
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    @Value("\${app.secret}") private val secret: String,
    @Value("\${app.expiration}") private val expirationInMs: Long
) {
    private val key: SecretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret))

    fun generateToken(user: UserEntity): String {
        val now = Date()
        val expirationDate = Date(now.time + expirationInMs)

        return Jwts.builder()
            .subject(user.email)
            .claim("role", user.role)
            .issuedAt(now)
            .expiration(expirationDate)
            .signWith(key)
            .compact()
    }

    fun getEmailFromToken(token: String): String {
        val claims: Claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
        return claims.subject
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
            return true
        } catch (ex: Exception) {
        }
        return false
    }
}