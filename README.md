# Kotlin Spring Security User Example

Este projeto é um exemplo de autenticação JWT usando Spring Boot, Kotlin, JPA e banco de dados H2 em memória. Ele demonstra como proteger rotas, registrar e autenticar usuários, e utilizar filtros personalizados para validação de tokens.

## Tecnologias Utilizadas
- Kotlin
- Spring Boot
- Spring Security
- Spring Data JPA
- H2 Database (em memória)
- JWT (JSON Web Token)

## Como rodar o projeto
1. **Pré-requisitos:**
   - Java 21+
   - Gradle (ou use o wrapper `gradlew`)

2. **Configuração:**
   - As configurações principais estão em `src/main/resources/application.yaml`.
   - O banco H2 é configurado para rodar em memória e pode ser acessado via `/h2-console`.
   - O segredo e tempo de expiração do JWT estão em `app.secret` e `app.expiration`.

3. **Executando:**
   ```sh
   ./gradlew bootRun
   ```
   Ou no Windows:
   ```cmd
   gradlew.bat bootRun
   ```

## Principais Funcionalidades

### Autenticação
- **Registro:** `POST /auth/register`
  - Body: `{ "name": "Nome", "email": "email@exemplo.com", "password": "senha", "role": "ADMIN" }`
- **Login:** `POST /auth/login`
  - Body: `{ "email": "email@exemplo.com", "password": "senha" }`
  - Retorna: `{ "token": "<jwt>" }`

### Proteção de Rotas
- Rotas `/auth/**` e `/h2-console/**` são públicas.
- Demais rotas exigem autenticação via JWT.
- Exemplo de rota protegida:
  - `GET /users` (apenas para usuários com papel ADMIN)

### Estrutura do Projeto
- `entity/UserEntity.kt`: Entidade de usuário, implementa `UserDetails`.
- `config/SecurityConfig.kt`: Configuração do Spring Security.
- `config/JwtAuthenticationFilter.kt`: Filtro para validar JWT em cada requisição.
- `config/JwtTokenProvider.kt`: Geração e validação de tokens JWT.
- `controller/AuthController.kt`: Endpoints de autenticação.
- `controller/UserController.kt`: Endpoints protegidos para usuários.

## Banco de Dados H2
- O banco roda em memória, não exige configuração extra.
- Console acessível em `/h2-console` (usuário: `sa`, senha: vazio).
- O modo está configurado para PostgreSQL para maior compatibilidade de tipos.

## Observações
- O campo `role` da entidade está como Enum, persistido como String para evitar problemas de compatibilidade de tipos.
- O filtro JWT é executado antes do filtro padrão de autenticação.
- O projeto segue boas práticas e está organizado para fácil entendimento e manutenção.


---

Sinta-se à vontade para adaptar este projeto para suas necessidades!

