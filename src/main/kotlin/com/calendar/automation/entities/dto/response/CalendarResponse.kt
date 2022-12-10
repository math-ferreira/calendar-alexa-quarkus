package com.calendar.automation.entities.dto.response

import com.calendar.automation.entities.dto.client.googleapicalendar.GoogleCalendarClientResponse

data class CalendarResponse(
    val calendarId: String,
    val summary: String,
    val etag: String,
    val timeZone: String
)

fun GoogleCalendarClientResponse.toCalendarResponse() =
    CalendarResponse(
        calendarId = id,
        summary = summary,
        etag = etag,
        timeZone = timeZone
    )
