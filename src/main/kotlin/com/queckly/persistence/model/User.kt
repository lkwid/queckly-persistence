package com.queckly.persistence.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
@Suppress("unused")
class User(
    @Id val id: Long,
    val oktaId: String,
    val firstName: String?,
    val lastName: String?,
    @Indexed(unique = true) val email: String,
    val password: String
) {
    constructor(id: Long, oktaId: String, email: String, password: String) : this(
        id,
        oktaId,
        null,
        null,
        email,
        password
    )

    companion object {
        const val SEQUENCE_NAME = "user_sequence"
    }

}
