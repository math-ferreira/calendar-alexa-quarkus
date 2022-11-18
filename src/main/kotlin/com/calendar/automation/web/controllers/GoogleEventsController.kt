package com.calendar.automation.web.controllers

import com.calendar.automation.entities.dto.GoogleEventsBody
import com.calendar.automation.entities.dto.GoogleEventsResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Tag(
    name = "google-events",
    description = "Provide integration to events inside the calendars"
)
@Path("/google-events")
interface GoogleEventsController {

    @POST
    @Consumes(APPLICATION_JSON)
    @Path("/insert/{calendarId}")
    fun insertEvent(
        @PathParam(value = "calendarId") calendarId: String,
        googleEventsBody: GoogleEventsBody
    ): GoogleEventsResponse

}
