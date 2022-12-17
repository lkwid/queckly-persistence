package com.queckly.persistence.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document("user")
@Suppress("unused")
class User (
    @Id val id: Long?,
    val oktaId: String,
    val firstName: String?,
    val lastName: String?,
    val email: String
)
