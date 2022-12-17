package com.queckly.persistence.service

import com.queckly.persistence.model.User
import com.queckly.persistence.repository.UserRepo
import mu.KLogging
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(private val userRepo: UserRepo) : KLogging() {
    fun save(user: User) {
        val savedUser: Mono<User> = userRepo.save(user)
        logger.info { savedUser.block()?.id }
    }

//    fun getById(id: Long) {
//
//    }
}