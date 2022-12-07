package com.calendar.automation.usecases.service.impl

import com.calendar.automation.client.GoogleAPICalendarClient
import com.calendar.automation.entities.dto.GoogleCalendarListResponse
import com.calendar.automation.entities.dto.client.googleapicalendar.CalendarListClientResponse
import com.calendar.automation.entities.dto.old.GoogleCalendarResponse
import com.calendar.automation.entities.dto.toCalendarListResponse
import com.calendar.automation.usecases.service.GoogleOauthService
import com.calendar.automation.usecases.service.GoogleCalendarService
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class GoogleCalendarServiceImpl(
    private val googleOauthService: GoogleOauthService,
    @RestClient private val googleCalendarClient: GoogleAPICalendarClient
) : GoogleCalendarService {

    override fun getCalendarList(): List<GoogleCalendarListResponse> {
        val authorization = googleOauthService.getToken()

        return buildAuthorization(authorization.accessToken)
            .run {
                googleCalendarClient.getCalendarList(this)
            }.toCalendarListResponse()
    }

    private fun buildAuthorization(accessToken: String) = "Bearer $accessToken"

    override fun getCalendarById(calendarId: String): GoogleCalendarResponse {
        /*        val googleCalendarResponse = googleAuthCredentialService.buildGoogleRequest()
                val calendarObject = googleCalendarResponse.calendars().get(calendarId).execute()
                return calendarObject.toGoogleCalendarResponse()*/
        TODO()
    }

}