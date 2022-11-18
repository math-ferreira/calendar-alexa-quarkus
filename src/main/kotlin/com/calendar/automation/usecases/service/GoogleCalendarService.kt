package com.calendar.automation.usecases.service

import com.calendar.automation.entities.dto.GoogleCalendarListResponse
import com.calendar.automation.entities.dto.GoogleCalendarResponse

interface GoogleCalendarService {

    fun getCalendarList(): List<GoogleCalendarListResponse>

    fun getCalendarById(calendarId: String): GoogleCalendarResponse
}