package com.calendar.automation.entities.dto.old

import com.google.api.services.calendar.model.CalendarList

data class GoogleCalendarListResponse(
    val calendarId: String,
    val summary: String,
    val isPrimary: Boolean
)

fun CalendarList.toGoogleCalendarListResponse() =
    this.items.map {
        GoogleCalendarListResponse(
            calendarId = it.id,
            summary = it.summary,
            isPrimary = it.isPrimary
        )
    }
