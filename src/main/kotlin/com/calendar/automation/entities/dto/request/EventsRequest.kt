package com.calendar.automation.entities.dto.request

import com.calendar.automation.entities.enums.EventTypeEnum
import com.fasterxml.jackson.annotation.JsonProperty

data class EventsRequest(
    val googleEventsQueries: EventsQueries,
    val googleEventsBody: EventsRequestBody,
)

data class EventsQueries(
    val calendarId: String
)

data class EventsRequestBody(
    @JsonProperty("send_updates")
    val sendUpdates: Boolean,
    @JsonProperty("attendees")
    val attendees: List<EventsAttendeesRequestBody>? = emptyList(),
    @JsonProperty("color_id")
    val colorId: String,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("event_type")
    val eventType: EventTypeEnum,
    @JsonProperty("start_date")
    val startDate: String,
    @JsonProperty("end_date")
    val endDate: String,
    @JsonProperty("location")
    val location: String,
    @JsonProperty("summary")
    val summary: String,
    @JsonProperty("reminders")
    val reminders: EventsRemindersRequestBody?
)

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