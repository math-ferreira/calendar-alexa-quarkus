package com.calendar.automation.entities.dto

import com.google.api.services.calendar.model.Event

data class GoogleEventsResponse(
    val summary: String? = null,
    val description: String? = null,
    val eventType: String? = null,
    val created: String?,
    val location: String? = null,
    val htmlLink: String? = null,
    val creatorEmail: String? = null,
    val startEvent: EventDateResponse? = null,
    val endEvent: EventDateResponse? = null
)

data class EventDateResponse(
    val dateTime: String,
    val timeZone: String
)

fun Event.toGoogleEventsResponse() =
    GoogleEventsResponse(
        summary = summary,
        description = description,
        eventType = eventType,
        created = created.toString(),
        location = location,
        htmlLink = htmlLink,
        creatorEmail = creator.email,
        startEvent = EventDateResponse(start.dateTime.toString(), start.timeZone),
        endEvent = EventDateResponse(end.dateTime.toString(), end.timeZone),
    )

