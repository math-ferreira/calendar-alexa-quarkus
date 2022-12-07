package com.calendar.automation.entities.dto

import com.calendar.automation.entities.dto.client.googleapicalendar.CalendarListClientResponse

data class GoogleCalendarListResponse(
    val calendarId: String,
    val summary: String,
    val isPrimary: Boolean,
    val accessRole: String
)

fun CalendarListClientResponse.toCalendarListResponse() =
    items.map {
        GoogleCalendarListResponse(
            calendarId = it.id,
            summary = it.summary,
            isPrimary = it.primary,
            accessRole = it.accessRole
        )
    }
