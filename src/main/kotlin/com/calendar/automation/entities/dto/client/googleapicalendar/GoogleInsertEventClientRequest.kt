package com.calendar.automation.entities.dto.client.googleapicalendar

import com.calendar.automation.entities.dto.request.EventsAttendeesRequestBody
import com.calendar.automation.entities.dto.request.EventsRequest
import com.calendar.automation.entities.enums.EventTypeEnum
import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleInsertEventClientRequest(
    @JsonProperty("summary")
    val summary: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("eventType")
    val eventType: String,
    @JsonProperty("location")
    val location: String,
    @JsonProperty("colorId")
    val colorId: String,
    @JsonProperty("start")
    val eventStartDate: DateTimeClientRequest,
    @JsonProperty("end")
    val eventEndDate: DateTimeClientRequest,
    @JsonProperty("attendees")
    val attendees: List<AttendeesClientRequest>,
)

data class DateTimeClientRequest(
    @JsonProperty("dateTime")
    val dateTime: String
)

data class AttendeesClientRequest(
    @JsonProperty("email")
    val email: String,
    @JsonProperty("displayName")
    val displayName: String,
    @JsonProperty("optional")
    val optional: Boolean,
)

fun EventsRequest.toGoogleInsertEventClientRequest() =
    with(googleEventsBody) {
        GoogleInsertEventClientRequest(
            summary = summary,
            colorId = colorId,
            description = description,
            eventType = EventTypeEnum.getValue(eventType),
            location = location,
            eventStartDate = DateTimeClientRequest(startDate),
            eventEndDate = DateTimeClientRequest(endDate),
            attendees = attendees.toAttendeesClientRequest()
        )
    }

fun List<EventsAttendeesRequestBody>.toAttendeesClientRequest() =
    map {
        AttendeesClientRequest(
            email = it.email,
            displayName = it.displayName,
            optional = it.isOptional
        )
    }