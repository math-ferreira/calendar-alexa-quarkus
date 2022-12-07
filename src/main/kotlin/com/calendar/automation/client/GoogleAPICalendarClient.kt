package com.calendar.automation.client

import com.calendar.automation.entities.dto.client.googleapicalendar.CalendarListClientResponse
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.POST
import javax.ws.rs.Path

@RegisterRestClient(configKey = "google-calendar")
interface GoogleAPICalendarClient {

    @GET
    @Path(value = "/v3/users/me/calendarList")
    fun getCalendarList(
        @HeaderParam(value = "Authorization") authorization: String
    ): CalendarListClientResponse
}
