package com.calendar.automation.web.controllers.impl

import com.calendar.automation.entities.dto.GoogleCalendarListResponse
import com.calendar.automation.entities.dto.client.googleapicalendar.CalendarListClientResponse
import com.calendar.automation.entities.dto.old.GoogleCalendarResponse
import com.calendar.automation.entities.enums.PermissionEnum.GOOGLE_CALENDAR
import com.calendar.automation.usecases.service.AuthorizationService
import com.calendar.automation.usecases.service.GoogleCalendarService
import com.calendar.automation.web.controllers.GoogleCalendarController
import javax.ws.rs.core.SecurityContext

class GoogleCalendarControllerImpl(
    private val authorizationService: AuthorizationService,
    private val googleCalendarService: GoogleCalendarService
) : GoogleCalendarController {

    override fun getCalendars(securityContext: SecurityContext): List<GoogleCalendarListResponse> {
        authorizeRequest(securityContext)
        return googleCalendarService.getCalendarList()
    }

    override fun getCalendarById(
        securityContext: SecurityContext,
        calendarId: String
    ): GoogleCalendarResponse {
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