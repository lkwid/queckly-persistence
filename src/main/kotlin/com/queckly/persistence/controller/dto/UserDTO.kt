package com.queckly.persistence.controller.dto

import java.io.Serializable

class UserDTO (
   val id: String,
   val firstName: String?,
   val lastName: String?,
   val email: String
) : Serializable