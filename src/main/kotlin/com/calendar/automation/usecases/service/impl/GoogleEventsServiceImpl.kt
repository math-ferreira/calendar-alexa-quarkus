package com.calendar.automation.usecases.service.impl

import com.calendar.automation.client.GoogleAPICalendarClient
import com.calendar.automation.entities.dto.request.EventsRequest
import com.calendar.automation.entities.dto.response.EventsResponse
import com.calendar.automation.entities.dto.client.googleapicalendar.GoogleInsertEventClientResponse
import com.calendar.automation.entities.dto.client.googleapicalendar.toGoogleInsertEventClientRequest
import com.calendar.automation.entities.dto.response.toEventsResponse
import com.calendar.automation.entities.extensions.toAuthorization
import com.calendar.automation.usecases.service.GoogleEventsService
import com.calendar.automation.usecases.service.GoogleOauthService
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GoogleEventsServiceImpl(
    private val googleOauthService: GoogleOauthService,
    @RestClient private val googleCalendarClient: GoogleAPICalendarClient
) : GoogleEventsService {

    override fun insertEvent(googleEventsRequest: EventsRequest): EventsResponse {
        return requestInsertEvent(googleEventsRequest)
            .run {
                this.toEventsResponse()
            }
    }

    private fun requestInsertEvent(googleEventsRequest: EventsRequest): GoogleInsertEventClientResponse {

        val authorization = googleOauthService.getToken()

        return authorization.accessToken.toAuthorization()
            .run {
                googleCalendarClient.insertEvent(
                    authorization = this,
                    calendarId = googleEventsRequest.googleEventsQueries.calendarId,
                    googleEventClientRequest = googleEventsRequest.toGoogleInsertEventClientRequest()
                )
            }
    }
}