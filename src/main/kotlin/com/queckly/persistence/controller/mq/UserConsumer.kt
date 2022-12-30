package com.queckly.persistence.controller.mq


import com.queckly.persistence.controller.dto.UserDto
import com.queckly.persistence.model.User
import com.queckly.persistence.repository.SequenceGenerator
import com.queckly.persistence.service.UserService
import kotlinx.coroutines.runBlocking
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
@Suppress("unused")
class UserConsumer(private val service: UserService, private val sequenceGenerator: SequenceGenerator) {
    suspend fun convertToUser(userDto: UserDto) = User(
        id = sequenceGenerator.generateSequence(User.SEQUENCE_NAME),
        oktaId = userDto.id,
        firstName = userDto.firstName,
        lastName = userDto.lastName,
        email = userDto.email,
        password = userDto.password
    )

    // TODO: Add configuration property for Queue names
    @RabbitListener(queues = ["q.users.register"])
    fun listen(userDto: UserDto) {
        runBlocking {
            service.save(convertToUser(userDto))
        }
    }
}
