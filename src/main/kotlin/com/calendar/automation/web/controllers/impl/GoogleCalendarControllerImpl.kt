package com.calendar.automation.web.controllers.impl

import com.calendar.automation.entities.dto.response.CalendarListResponse
import com.calendar.automation.entities.dto.response.CalendarResponse
import com.calendar.automation.entities.enums.RoleEnum.GOOGLE_CALENDAR
import com.calendar.automation.usecases.service.AuthorizationService
import com.calendar.automation.usecases.service.GoogleCalendarService
import com.calendar.automation.web.controllers.GoogleCalendarController
import javax.ws.rs.core.SecurityContext

class GoogleCalendarControllerImpl(
    private val authorizationService: AuthorizationService,
    private val googleCalendarService: GoogleCalendarService
) : GoogleCalendarController {

    override fun getCalendars(securityContext: SecurityContext): List<CalendarListResponse> {
        authorizeRequest(securityContext)
        return googleCalendarService.getCalendarList()
    }

    override fun getCalendarById(
        securityContext: SecurityContext,
        calendarId: String
    ): CalendarResponse {
        authorizeRequest(securityContext)
        return googleCalendarService.getCalendarById(calendarId)
    }

    private fun authorizeRequest(securityContext: SecurityContext) {
        authorizationService.validateRequest(
            securityContext = securityContext,
            allowedRoles = listOf(GOOGLE_CALENDAR)
        )
    }
}