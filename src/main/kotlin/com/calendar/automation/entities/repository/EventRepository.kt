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
            description = data.description
            htmlLink = data.htmlLink
            startDateTime = data.eventStartDate.dateTime.toLocalDateTime()
            endDateTime = data.eventEndDate.dateTime.toLocalDateTime()
            createdAt = data.created.toLocalDateTime()
            attendes = data.attendees.joinToString(separator = COMMA)
        }.apply {
            this.persist()
        }

    companion object {
        const val COMMA = ", "
    }
}