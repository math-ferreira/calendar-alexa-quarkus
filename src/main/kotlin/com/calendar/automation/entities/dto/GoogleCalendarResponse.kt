package com.calendar.automation.entities.dto

import com.google.api.services.calendar.model.Calendar

data class GoogleCalendarResponse(
    val kind: String? = null,
    val etag: String? = null,
    val calendarId: String? = null,
    val summary: String? = null,
    val description: String? = null,
    val location: String? = null,
    val timeZone: String? = null,
    val allowedConferenceSolutionTypes: List<String>? = emptyList()
)

fun Calendar.toGoogleCalendarResponse() =
    GoogleCalendarResponse(
        kind = kind,
        etag = etag,
        calendarId = id,
        summary = summary,
        description = description,
        location = location,
        timeZone = timeZone,
        allowedConferenceSolutionTypes = conferenceProperties.allowedConferenceSolutionTypes
    )