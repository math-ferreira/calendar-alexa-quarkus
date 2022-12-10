package com.calendar.automation.client

import com.calendar.automation.entities.dto.client.googleapicalendar.GoogleCalendarClientResponse
import com.calendar.automation.entities.dto.client.googleapicalendar.GoogleCalendarListClientResponse
import com.calendar.automation.entities.dto.client.googleapicalendar.GoogleInsertEventClientRequest
import com.calendar.automation.entities.dto.client.googleapicalendar.GoogleInsertEventClientResponse
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@RegisterRestClient(configKey = "google-calendar")
interface GoogleAPICalendarClient {

    @GET
    @Path(value = "/v3/users/me/calendarList")
    fun getCalendarList(
        @HeaderParam(value = AUTHORIZATION) authorization: String
    ): GoogleCalendarListClientResponse

    @GET
    @Path(value = "/v3/calendars/{calendarId}")
    fun getCalendarById(
        @HeaderParam(value = AUTHORIZATION) authorization: String,
        @PathParam(value = CALENDAR_ID) calendarId: String
    ): GoogleCalendarClientResponse

    @POST
    @Consumes(APPLICATION_JSON)
    @Path(value = "/v3/calendars/{calendarId}/events")
    fun insertEvent(
        @HeaderParam(value = AUTHORIZATION) authorization: String,
        @PathParam(value = CALENDAR_ID) calendarId: String,
        googleEventClientRequest: GoogleInsertEventClientRequest
    ): GoogleInsertEventClientResponse

    companion object {
        const val AUTHORIZATION = "Authorization"
        const val CALENDAR_ID = "calendarId"
    }
}
