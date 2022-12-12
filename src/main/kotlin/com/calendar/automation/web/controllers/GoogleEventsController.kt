package com.calendar.automation.web.controllers

import com.calendar.automation.entities.constants.PermissionsConstants.GOOGLE_EVENTS_ROLE_NAME
import com.calendar.automation.entities.dto.request.EventsRequestBody
import com.calendar.automation.entities.dto.response.EventsResponse
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.annotation.security.RolesAllowed
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.SecurityContext

@Tag(
    name = "google-events",
    description = "Provide integration to events inside the calendars"
)
@Path("/google-events")
interface GoogleEventsController {

    @POST
    @Consumes(APPLICATION_JSON)
    @RolesAllowed(GOOGLE_EVENTS_ROLE_NAME)
    @Path("/insert/{calendarId}")
    fun insertEvent(
        @Context securityContext: SecurityContext,
        @PathParam(value = "calendarId") calendarId: String,
        @RequestBody googleEventsBody: EventsRequestBody
    ): EventsResponse

}
