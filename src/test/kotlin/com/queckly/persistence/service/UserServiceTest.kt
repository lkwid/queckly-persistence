package com.queckly.persistence.service

import com.queckly.persistence.MongoSpringBootTest
import com.queckly.persistence.TestProfiles
import com.queckly.persistence.model.User
import com.queckly.persistence.repository.SequenceGenerator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.dao.DuplicateKeyException
import java.util.*

@Profile(TestProfiles.INTEGRATION)
@ExperimentalCoroutinesApi
@MongoSpringBootTest
class UserServiceTest {

    @Autowired
    lateinit var service: UserService

    @Autowired
    lateinit var sequenceGenerator: SequenceGenerator

    @Test
    fun `save new User then delete`() = runTest {
        // given
        val email = "just-a-test@queckly.com"
        val user = User(
            sequenceGenerator.generateSequence(User.SEQUENCE_NAME),
            "1234567890",
            "Testus",
            "Testowiecki",
            email,
            "pass01"
        )
        // when
        service.save(user)
        // then
        assertTrue((service.getAll().count().block() ?: 0) > 0)
        // when
        service.deleteByEmail(email)
        //then
        assertNull(service.getByEmail(user.email))
    }

    @Test
    fun `forbid saving a duplicated email`() = runTest {
        // given
        val email = "just-a-test@queckly.com"

        service.save(
            namelessUser(
                sequenceGenerator.generateSequence(User.SEQUENCE_NAME),
                email
            )
        )
        // then
        assertThrows<DuplicateKeyException> {
            runBlocking {
                service.save(
                    namelessUser(
                        sequenceGenerator.generateSequence(User.SEQUENCE_NAME),
                        email
                    )
                )
            }
        }
    }

    companion object {
        fun namelessUser(id: Long, email: String) = User(
            id,
            "1234567890",
            null,
            null,
            email,
            "pass01"
        )
    }
}
