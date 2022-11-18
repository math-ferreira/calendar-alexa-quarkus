package com.calendar.automation.usecases.service.impl

import com.calendar.automation.entities.dto.*
import com.calendar.automation.usecases.service.GoogleAuthCredentialService
import com.calendar.automation.usecases.service.GoogleEventsService
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventAttendee
import com.google.api.services.calendar.model.EventDateTime
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GoogleEventsServiceImpl(
    private val googleAuthCredentialService: GoogleAuthCredentialService
) : GoogleEventsService {

    override fun insertEvent(googleEventsRequest: GoogleEventsRequest): GoogleEventsResponse {
        val eventResponse = requestInsertEvent(googleEventsRequest)
        return eventResponse.toGoogleEventsResponse()
    }

    private fun requestInsertEvent(googleEventsRequest: GoogleEventsRequest): Event {
        return buildEvent(googleEventsRequest.googleEventsBody)
            .apply {
                start = setEventDateTime(googleEventsRequest.googleEventsBody.startDate)
                end = setEventDateTime(googleEventsRequest.googleEventsBody.endDate)
                attendees = setEventAttendees(googleEventsRequest.googleEventsBody.attendees)
            }
            .run {
                googleAuthCredentialService.buildGoogleRequest()
                    .events()
                    .insert(googleEventsRequest.googleEventsQueries.calendarId, this)
                    .execute()
            }
    }

    private fun buildEvent(googleEventsBody: GoogleEventsBody) =
        with(googleEventsBody) {
            Event()
                .setSummary(summary)
                .setLocation(location)
                .setDescription(description)
                .setColorId(colorId)
        }

    private fun setEventDateTime(dateTime: String) =
        DateTime(dateTime).run {
            EventDateTime().setDateTime(this)
        }

    private fun setEventAttendees(attendees: List<GoogleEventsAttendees>) =
        attendees.map {
            EventAttendee()
                .setDisplayName(it.displayName)
                .setEmail(it.email)
                .setOptional(it.isOptional)
                .setResponseStatus(it.responseStatus)
        }

}