package com.calendar.automation.usecases.service.impl

import com.calendar.automation.client.GoogleAPICalendarClient
import com.calendar.automation.entities.dto.response.CalendarListResponse
import com.calendar.automation.entities.dto.response.CalendarResponse
import com.calendar.automation.entities.dto.response.toCalendarListResponse
import com.calendar.automation.entities.dto.response.toCalendarResponse
import com.calendar.automation.entities.extensions.toAuthorization
import com.calendar.automation.usecases.service.GoogleCalendarService
import com.calendar.automation.usecases.service.GoogleOauthService
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class GoogleCalendarServiceImpl(
    private val googleOauthService: GoogleOauthService,
    @RestClient private val googleCalendarClient: GoogleAPICalendarClient
) : GoogleCalendarService {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun getCalendarList(): List<CalendarListResponse> {

        logger.info("${this::class.simpleName} -> Starting to get calendar list")

        val authorization = googleOauthService.getToken()

        return authorization.accessToken.toAuthorization()
            .run {
                googleCalendarClient.getCalendarList(this)
            }.toCalendarListResponse()
            .also {
                logger.info("${this::class.simpleName} -> Calendar list found with success [size: ${it.size}]")
            }
    }

    override fun getCalendarById(calendarId: String): CalendarResponse {

        logger.info("${this::class.simpleName} -> Starting to get calendar details by id [calendarId: $calendarId]")

        val authorization = googleOauthService.getToken()

        return authorization.accessToken.toAuthorization()
            .run {
                googleCalendarClient.getCalendarById(
                    authorization = this,
                    calendarId = calendarId
                )
            }.toCalendarResponse()
            .also {
                logger.info("${this::class.simpleName} -> Calendar details found with success [calendarId: $calendarId]")
            }
    }

}