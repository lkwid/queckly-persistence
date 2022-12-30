package com.queckly.persistence.repository

import com.queckly.persistence.model.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : ReactiveMongoRepository<User, Long> {
    suspend fun save(user: User): User
    suspend fun deleteByEmail(email: String)
    suspend fun findByEmail(email: String): User
}