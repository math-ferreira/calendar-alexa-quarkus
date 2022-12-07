package com.calendar.automation.entities.dto

import com.calendar.automation.entities.dto.client.googleapicalendar.GoogleCalendarListClientResponse

data class CalendarListResponse(
    val calendarId: String,
    val summary: String,
    val isPrimary: Boolean,
    val accessRole: String
)

fun GoogleCalendarListClientResponse.toCalendarListResponse() =
    items.map {
        CalendarListResponse(
            calendarId = it.id,
            summary = it.summary,
            isPrimary = it.primary,
            accessRole = it.accessRole
        )
    }
