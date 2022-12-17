package com.queckly.persistence.controller.mq


import com.queckly.persistence.controller.dto.UserDTO
import com.queckly.persistence.model.User
import com.queckly.persistence.service.UserService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
@Suppress("unused")
class UserConsumer(val service: UserService) {
    companion object {
        fun convertToUser(userDTO: UserDTO) = User(
            id = userDTO.id.toLong(),
            oktaId = userDTO.id,
            firstName = userDTO.firstName,
            lastName = userDTO.lastName,
            email = userDTO.email
        )
    }
    // TODO: Add configuration property for Queue names
    @RabbitListener(queues = ["q.users.register"])
    fun listen(userDTO: UserDTO) {
        service.save(convertToUser(userDTO))
    }
}
