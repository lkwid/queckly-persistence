package com.queckly.persistence

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.MongoDBContainer

class MongoInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(context: ConfigurableApplicationContext) {
        val addedProperties = listOf(
            "spring.data.mongodb.uri=${MongoContainerSingleton.instance.replicaSetUrl}"
        )
        TestPropertyValues.of(addedProperties).applyTo(context.environment)
    }
}

object MongoContainerSingleton {
    val instance by lazy { startMongoContainer() }
    private fun startMongoContainer(): MongoDBContainer =
        MongoDBContainer("mongo")
            .withPrivilegedMode(true)
            .withReuse(true)
            .apply { start() }
}