package com.queckly.persistence.repository

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Suppress("unused")
@Document(collection = "database_sequences")
class Sequences(
    @Id val id: String,
    val sequence: Long?
)
