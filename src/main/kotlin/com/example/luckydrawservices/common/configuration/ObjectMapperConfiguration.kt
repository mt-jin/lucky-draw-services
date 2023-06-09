package com.example.luckydrawservices.common.configuration

import com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.time.Instant
import java.time.format.DateTimeFormatterBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class ObjectMapperConfiguration {
    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            registerKotlinModule()
            configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
            configure(ALLOW_COMMENTS, true)
            configure(WRITE_DATES_AS_TIMESTAMPS, false)

            enable(USE_BIG_DECIMAL_FOR_FLOATS)

            registerModule(
                JavaTimeModule().apply {
                    addSerializer(
                        Instant::class.java,
                        Iso8601WithoutMillisInstantSerializer()
                    )
                }
            )
        }
    }

    private inner class Iso8601WithoutMillisInstantSerializer :
        InstantSerializer(
            INSTANCE, false,
            DateTimeFormatterBuilder().appendInstant(INSTANT_FRACTIONAL_DIGITS).toFormatter()
        )

    private companion object {
        const val INSTANT_FRACTIONAL_DIGITS = 3
    }
}
