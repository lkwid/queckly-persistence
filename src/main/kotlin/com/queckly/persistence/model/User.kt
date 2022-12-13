package com.queckly.persistence.model

import com.queckly.app.controller.dto.UserDTO

// TODO: try with reactive types
class User (val oktaId: Long, val firstName: String?, val lastName: String?, val email: String) {
    companion object {
        fun convertToUser(userType: UserDTO) = User(
            oktaId = userType.id,
            firstName = userType.firstName,
            lastName = userType.lastName,
            email = userType.email
        )
    }
}

