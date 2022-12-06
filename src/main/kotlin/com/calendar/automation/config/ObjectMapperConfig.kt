package com.calendar.automation.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE
import io.quarkus.jackson.ObjectMapperCustomizer
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ObjectMapperConfig: ObjectMapperCustomizer {

    override fun customize(objectMapper: ObjectMapper) {
        objectMapper.propertyNamingStrategy = SNAKE_CASE
    }
}