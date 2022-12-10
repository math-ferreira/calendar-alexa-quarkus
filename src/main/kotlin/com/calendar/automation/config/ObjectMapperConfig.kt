package com.calendar.automation.config

import com.calendar.automation.config.serialize.EventsDateResponseSerialize
import com.calendar.automation.entities.dto.response.EventsDateResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE
import com.fasterxml.jackson.databind.module.SimpleModule
import io.quarkus.jackson.ObjectMapperCustomizer
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ObjectMapperConfig : ObjectMapperCustomizer {

    override fun customize(objectMapper: ObjectMapper) {
        objectMapper.propertyNamingStrategy = SNAKE_CASE
        val simpleModule = createSimpleModuleAndSerializers()
        objectMapper.registerModule(simpleModule)
    }

    private fun createSimpleModuleAndSerializers(): SimpleModule {
        return SimpleModule()
            .addSerializer(
                EventsDateResponse::class.java,
                EventsDateResponseSerialize()
            )
    }
}