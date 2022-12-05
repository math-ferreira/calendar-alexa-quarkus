package com.calendar.automation.usecases.service.impl

import com.calendar.automation.entities.dto.old.GoogleCalendarListResponse
import com.calendar.automation.entities.dto.old.GoogleCalendarResponse
import com.calendar.automation.usecases.service.GoogleOauthService
import com.calendar.automation.usecases.service.GoogleCalendarService
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class GoogleCalendarServiceImpl(
    private val googleAuthCredentialService: GoogleOauthService
) : GoogleCalendarService {

    override fun getCalendarList(): List<GoogleCalendarListResponse> {
/*        val googleCalendarResponse = googleAuthCredentialService.buildGoogleRequest()

        val calendarListObject = googleCalendarResponse
            .calendarList()
            .list()
            .execute()

        return calendarListObject.toGoogleCalendarListResponse()*/
        TODO()
    }

    override fun getCalendarById(calendarId: String): GoogleCalendarResponse {
/*        val googleCalendarResponse = googleAuthCredentialService.buildGoogleRequest()
        val calendarObject = googleCalendarResponse.calendars().get(calendarId).execute()
        return calendarObject.toGoogleCalendarResponse()*/
        TODO()
    }

}