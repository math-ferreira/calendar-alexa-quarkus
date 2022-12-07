package com.calendar.automation.usecases.service

import com.calendar.automation.entities.dto.CalendarListResponse
import com.calendar.automation.entities.dto.CalendarResponse

interface GoogleCalendarService {

    fun getCalendarList(): List<CalendarListResponse>

    fun getCalendarById(calendarId: String): CalendarResponse
}