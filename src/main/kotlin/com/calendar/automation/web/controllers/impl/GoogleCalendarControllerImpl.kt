package com.calendar.automation.web.controllers.impl

import com.calendar.automation.entities.dto.GoogleCalendarListResponse
import com.calendar.automation.entities.dto.GoogleCalendarResponse
import com.calendar.automation.usecases.service.GoogleCalendarService
import com.calendar.automation.web.controllers.GoogleCalendarController

class GoogleCalendarControllerImpl(
    private val googleCalendarService: GoogleCalendarService
) : GoogleCalendarController {

    override fun getCalendars(): List<GoogleCalendarListResponse> {
        return googleCalendarService.getCalendarList()
    }

    override fun getCalendarById(calendarId: String): GoogleCalendarResponse{
        return googleCalendarService.getCalendarById(calendarId)
    }
}