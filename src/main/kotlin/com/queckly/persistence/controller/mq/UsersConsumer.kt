package com.queckly.persistence.controller.mq

import com.queckly.app.controller.dto.UserDTO
import com.queckly.persistence.model.User
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
@Suppress("unused")
class UsersConsumer {

    // TODO: Add configuration property for Queue names
    @RabbitListener(queues = ["q.users.register"])
    suspend fun listen(userDTO: UserDTO) {
        println(User.convertToUser(userDTO))
    }

}