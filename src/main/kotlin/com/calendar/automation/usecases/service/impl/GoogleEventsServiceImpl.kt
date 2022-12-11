package com.calendar.automation.usecases.service.impl

import com.calendar.automation.client.GoogleAPICalendarClient
import com.calendar.automation.entities.dto.request.EventsRequest
import com.calendar.automation.entities.dto.response.EventsResponse
import com.calendar.automation.entities.dto.client.googleapicalendar.GoogleInsertEventClientResponse
import com.calendar.automation.entities.dto.client.googleapicalendar.toGoogleInsertEventClientRequest
import com.calendar.automation.entities.dto.response.toEventsResponse
import com.calendar.automation.entities.extensions.toAuthorization
import com.calendar.automation.entities.repository.EventRepository
import com.calendar.automation.usecases.service.GoogleEventsService
import com.calendar.automation.usecases.service.GoogleOauthService
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class GoogleEventsServiceImpl(
    private val googleOauthService: GoogleOauthService,
    @RestClient private val googleCalendarClient: GoogleAPICalendarClient,
    private val eventRepository: EventRepository
) : GoogleEventsService {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    override fun insertEvent(googleEventsRequest: EventsRequest): EventsResponse {

        logger.info("${this::class.simpleName} -> Starting to insert new event in google calendar [calendarId: ${googleEventsRequest.googleEventsQueries.calendarId}]")

        return requestInsertEvent(googleEventsRequest)
            .run {
                this.toEventsResponse()
            }
            .also {
                logger.info("${this::class.simpleName} -> Google event created with success [calendarId: ${googleEventsRequest.googleEventsQueries.calendarId}]")
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
            }.apply {
                eventRepository.save(this)
            }.also {
                logger.info("${this::class.simpleName} -> Event saved in database [calendarId: ${googleEventsRequest.googleEventsQueries.calendarId}]")
            }
    }
}