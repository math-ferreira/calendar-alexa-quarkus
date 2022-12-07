package com.calendar.automation.client

import com.calendar.automation.entities.dto.client.googleapicalendar.GoogleCalendarClientResponse
import com.calendar.automation.entities.dto.client.googleapicalendar.GoogleCalendarListClientResponse
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.Path
import javax.ws.rs.PathParam

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

    companion object {
        const val AUTHORIZATION = "Authorization"
        const val CALENDAR_ID = "calendarId"
    }
}
