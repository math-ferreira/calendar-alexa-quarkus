package com.calendar.automation.web.controllers

import com.calendar.automation.entities.dto.GoogleCalendarListResponse
import com.calendar.automation.entities.dto.GoogleCalendarResponse
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam

@Path("/google-calendar")
interface GoogleCalendarController {

    @GET
    @Path("/calendars")
    fun getCalendars(): List<GoogleCalendarListResponse>

    @GET
    @Path("/calendar")
    fun getCalendarById(
        @QueryParam(value = "calendarId") calendarId: String
    ): GoogleCalendarResponse

}
