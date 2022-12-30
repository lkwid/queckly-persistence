package com.queckly.persistence.service

import com.queckly.persistence.model.User
import com.queckly.persistence.repository.UserRepo
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepo: UserRepo) : KLogging() {
    suspend fun save(user: User) {
        val savedUser: User = userRepo.save(user)
        logger.info { "New user registered: ${savedUser.id}" }
    }

    suspend fun deleteByEmail(email: String) {
        userRepo.deleteByEmail(email)
        logger.info { "User deleted: $email" }
    }

    suspend fun getByEmail(email: String) = userRepo.findByEmail(email)

    suspend fun getAll() = userRepo.findAll()
}