package com.calendar.automation.config.serialize

import com.calendar.automation.entities.dto.response.EventsDateResponse
import com.calendar.automation.entities.extensions.toOffsetDateTime
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer


class EventsDateResponseSerialize(T: Class<EventsDateResponse>? = null) : StdSerializer<EventsDateResponse>(T) {

    override fun serialize(
        value: EventsDateResponse,
        jgen: JsonGenerator,
        provider: SerializerProvider
    ) {

        runCatching {

            val offsetDateTime = value.dateTime.toOffsetDateTime()

            jgen.writeStartObject()

            jgen.writeStringField(DATE, offsetDateTime.toLocalDate().toString())
            jgen.writeStringField(TIME, offsetDateTime.toLocalTime().toString())
            jgen.writeStringField(DATE_TIME, offsetDateTime.toString())
            jgen.writeStringField(ZONE_OFFSET, offsetDateTime.offset.toString())

            jgen.writeEndObject()

        }.onFailure {
            println("Error to serialize ${this.javaClass}, error: ${it.message}")
        }
    }

    companion object {
        const val DATE = "date"
        const val TIME = "time"
        const val DATE_TIME = "date_time"
        const val ZONE_OFFSET = "zone_offset"
    }
}
