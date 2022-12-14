package com.queckly.persistence.system

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MqConfig {
//    @Bean
//    fun objectMapperWithKotlinModule(): ObjectMapper = ObjectMapper().registerModule(
//        KotlinModule.Builder()
//            .withReflectionCacheSize(512)
//            .configure(KotlinFeature.NullToEmptyCollection, true)
//            .configure(KotlinFeature.NullToEmptyMap, true)
//            .configure(KotlinFeature.NullIsSameAsDefault, true)
//            .configure(KotlinFeature.SingletonSupport, true)
//            .configure(KotlinFeature.StrictNullChecks, false)
//            .build()
//    )
    @Bean
    fun mapper() = jacksonObjectMapper()
    @Bean
    fun messageConverter(): MessageConverter = Jackson2JsonMessageConverter(mapper())
}
