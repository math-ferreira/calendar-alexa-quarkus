package com.calendar.automation.web.controllers

import com.calendar.automation.entities.constants.PermissionsConstants.GOOGLE_CALENDAR_ROLE_NAME
import com.calendar.automation.entities.dto.GoogleCalendarListResponse
import com.calendar.automation.entities.dto.client.googleapicalendar.CalendarListClientResponse
import com.calendar.automation.entities.dto.old.GoogleCalendarResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.annotation.security.RolesAllowed
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Context
import javax.ws.rs.core.SecurityContext

@Tag(
    name = "google-calendar",
    description = "Provide integration to calendar data"
)
@Path("/google-calendar")
interface GoogleCalendarController {

    @GET
    @RolesAllowed(GOOGLE_CALENDAR_ROLE_NAME)
    @Path("/calendars")
    fun getCalendars(
        @Context securityContext: SecurityContext
    ): List<GoogleCalendarListResponse>

    @GET
    @RolesAllowed(GOOGLE_CALENDAR_ROLE_NAME)
    @Path("/calendar")
    fun getCalendarById(
        @Context securityContext: SecurityContext,
        @QueryParam(value = "calendarId") calendarId: String
    ): GoogleCalendarResponse

}
