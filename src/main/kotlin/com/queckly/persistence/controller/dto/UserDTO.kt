package com.queckly.app.controller.dto

import java.io.Serializable

class UserDTO(val id: Long, val firstName: String?, val lastName: String?, val email: String) : Serializable