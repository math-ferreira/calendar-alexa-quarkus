package com.calendar.automation.web.controllers

import com.calendar.automation.entities.dto.GoogleCalendarListResponse
import com.calendar.automation.entities.dto.GoogleCalendarResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.annotation.security.RolesAllowed
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam

@Tag(
    name = "google-calendar",
    description = "Provide integration to calendar data"
)
@Path("/google-calendar")
interface GoogleCalendarController {

    @GET
    @Path("/calendars")
    @RolesAllowed("Tester")
    fun getCalendars(): List<GoogleCalendarListResponse>

    @GET
    @Path("/calendar")
    fun getCalendarById(
        @QueryParam(value = "calendarId") calendarId: String
    ): GoogleCalendarResponse

}
