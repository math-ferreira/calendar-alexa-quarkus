package com.calendar.automation.usecases.service.impl

import com.calendar.automation.client.GoogleAPICalendarClient
import com.calendar.automation.entities.dto.CalendarListResponse
import com.calendar.automation.entities.dto.CalendarResponse
import com.calendar.automation.entities.dto.toCalendarListResponse
import com.calendar.automation.entities.dto.toCalendarResponse
import com.calendar.automation.usecases.service.GoogleOauthService
import com.calendar.automation.usecases.service.GoogleCalendarService
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class GoogleCalendarServiceImpl(
    private val googleOauthService: GoogleOauthService,
    @RestClient private val googleCalendarClient: GoogleAPICalendarClient
) : GoogleCalendarService {

    override fun getCalendarList(): List<CalendarListResponse> {
        val authorization = googleOauthService.getToken()

        return buildAuthorization(authorization.accessToken)
            .run {
                googleCalendarClient.getCalendarList(this)
            }.toCalendarListResponse()
    }

    override fun getCalendarById(calendarId: String): CalendarResponse {
        val authorization = googleOauthService.getToken()

        return buildAuthorization(authorization.accessToken)
            .run {
                googleCalendarClient.getCalendarById(
                    authorization = this,
                    calendarId = calendarId
                )
            }.toCalendarResponse()
    }

    private fun buildAuthorization(accessToken: String) = "Bearer $accessToken"

}