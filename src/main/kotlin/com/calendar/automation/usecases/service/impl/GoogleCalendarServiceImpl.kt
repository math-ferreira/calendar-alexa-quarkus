package com.calendar.automation.usecases.service.impl

import com.calendar.automation.entities.dto.GoogleCalendarListResponse
import com.calendar.automation.entities.dto.GoogleCalendarResponse
import com.calendar.automation.entities.dto.toGoogleCalendarListResponse
import com.calendar.automation.entities.dto.toGoogleCalendarResponse
import com.calendar.automation.entities.exception.CustomException
import com.calendar.automation.usecases.service.GoogleAuthCredentialService
import com.calendar.automation.usecases.service.GoogleCalendarService
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.Response


@ApplicationScoped
class GoogleCalendarServiceImpl(
    private val googleAuthCredentialService: GoogleAuthCredentialService
) : GoogleCalendarService {

    override fun getCalendarList(): List<GoogleCalendarListResponse> {
        val googleCalendarResponse = googleAuthCredentialService.buildGoogleRequest()

        val calendarListObject = googleCalendarResponse
            .calendarList()
            .list()
            .execute()

        return calendarListObject.toGoogleCalendarListResponse()
    }

    override fun getCalendarById(calendarId: String): GoogleCalendarResponse {
        val googleCalendarResponse = googleAuthCredentialService.buildGoogleRequest()
        val calendarObject = googleCalendarResponse.calendars().get(calendarId).execute()
        return calendarObject.toGoogleCalendarResponse()
    }

}