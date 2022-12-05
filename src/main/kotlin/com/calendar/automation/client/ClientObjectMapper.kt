package com.calendar.automation.client

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import javax.ws.rs.ext.ContextResolver

class ClientObjectMapper : ContextResolver<ObjectMapper> {
    override fun getContext(type: Class<*>?): ObjectMapper {
        val om = ObjectMapper()
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        om.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
        return om
    }
}