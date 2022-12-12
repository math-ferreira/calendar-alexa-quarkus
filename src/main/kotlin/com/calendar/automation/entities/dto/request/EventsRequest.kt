package com.calendar.automation.entities.dto.request

import com.calendar.automation.entities.enums.EventTypeEnum
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class EventsRequest(
    val googleEventsQueries: EventsQueries,
    val googleEventsBody: EventsRequestBody,
)

data class EventsQueries(
    val calendarId: String
)

@JsonNaming(SnakeCaseStrategy::class)
data class EventsRequestBody(
    val sendUpdates: Boolean,
    val attendees: List<EventsAttendeesRequestBody>,
    val colorId: String,
    val description: String,
    val eventType: EventTypeEnum,
    val startDate: String,
    val endDate: String,
    val location: String,
    val summary: String,
    val reminders: EventsRemindersRequestBody?
)

@JsonNaming(SnakeCaseStrategy::class)
data class EventsAttendeesRequestBody(
    @JsonProperty("display_name")
    val displayName: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("is_optional")
    val isOptional: Boolean,
    @JsonProperty("response_status")
    val responseStatus: String
)

@JsonNaming(SnakeCaseStrategy::class)
data class EventsRemindersRequestBody(
    @JsonProperty("method")
    val method: String,
    @JsonProperty("trigger_minutes")
    val triggerMinutes: Int,
)

fun EventsRequestBody.toRequest(calendarId: String) =
    EventsRequest(
        googleEventsBody = this,
        googleEventsQueries = EventsQueries(
            calendarId = calendarId
        )
    )