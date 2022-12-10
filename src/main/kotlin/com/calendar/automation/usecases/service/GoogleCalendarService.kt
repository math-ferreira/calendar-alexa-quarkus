package com.calendar.automation.usecases.service

import com.calendar.automation.entities.dto.response.CalendarListResponse
import com.calendar.automation.entities.dto.response.CalendarResponse

interface GoogleCalendarService {

    fun getCalendarList(): List<CalendarListResponse>

    fun getCalendarById(calendarId: String): CalendarResponse
}