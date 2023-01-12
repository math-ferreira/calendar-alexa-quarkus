package com.calendar.automation.entities.repository

import com.calendar.automation.entities.dto.client.googleapicalendar.GoogleInsertEventClientResponse
import com.calendar.automation.entities.entity.Event
import com.calendar.automation.entities.extensions.toLocalDateTime
import io.quarkus.hibernate.orm.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class EventRepository : PanacheRepository<Event> {

    fun save(data: GoogleInsertEventClientResponse) =
        Event().apply {
            summary = data.summary
            description = data.description ?: NO_DESCRIPTION
            htmlLink = data.htmlLink
            startDateTime = data.eventStartDate.dateTime.toLocalDateTime()
            endDateTime = data.eventEndDate.dateTime.toLocalDateTime()
            createdAt = data.created.toLocalDateTime()
            attendes = data.attendees?.joinToString(separator = COMMA) ?: NO_ATTENDEES
        }.apply {
            this.persist()
        }

    companion object {
        const val COMMA = ", "
        const val NO_DESCRIPTION = "NO_DESCRIPTION"
        const val NO_ATTENDEES = "NO_ATTENDEES"
    }
}