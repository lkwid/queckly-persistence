package com.queckly.persistence.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service

@Service
class SequenceGenerator(private val mongoOperations: ReactiveMongoOperations) {
    suspend fun generateSequence(seqName: String) = withContext(Dispatchers.IO) {
        mongoOperations.findAndModify(
            Query(Criteria.where("_id").`is`(seqName)),
            Update().inc("sequence", 1),
            options().returnNew(true).upsert(true),
            Sequences::class.java
        ).toFuture().get()
    }.sequence!!

}
