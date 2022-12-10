package com.calendar.automation.entities.dto.response

import com.calendar.automation.entities.dto.client.googleapicalendar.AttendeesClientResponse
import com.calendar.automation.entities.dto.client.googleapicalendar.DateTimeClientResponse
import com.calendar.automation.entities.dto.client.googleapicalendar.GoogleInsertEventClientResponse

data class EventsResponse(
    val summary: String,
    val description: String,
    val eventType: String,
    val created: String,
    val location: String? = null,
    val htmlLink: String? = null,
    val creatorEmail: String,
    val attendees: List<EventsAttendeesResponse>? = emptyList(),
    val startEvent: EventsDateResponse,
    val endEvent: EventsDateResponse
)

data class EventsAttendeesResponse(
    val displayName: String,
    val email: String,
)

data class EventsDateResponse(
    val dateTime: String,
    val timeZone: String? = null
)

fun GoogleInsertEventClientResponse.toEventsResponse() =
    EventsResponse(
        summary = summary,
        description = description,
        eventType = eventType,
        created = created,
        location = location,
        htmlLink = htmlLink,
        creatorEmail = creator.email,
        attendees = attendees.toEventsAttendeesResponse(),
        startEvent = eventStartDate.toEventDateResponse(),
        endEvent = eventEndDate.toEventDateResponse()
    )

fun List<AttendeesClientResponse>.toEventsAttendeesResponse() =
    map {
        EventsAttendeesResponse(
            displayName = it.displayName,
            email = it.email
        )
    }

fun DateTimeClientResponse.toEventDateResponse() =
    EventsDateResponse(
        dateTime = dateTime,
        timeZone = timeZone
    )
