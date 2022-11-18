package com.calendar.automation.web.controllers.impl

import com.calendar.automation.entities.dto.GoogleEventsBody
import com.calendar.automation.entities.dto.GoogleEventsResponse
import com.calendar.automation.entities.dto.toRequest
import com.calendar.automation.usecases.service.GoogleEventsService
import com.calendar.automation.web.controllers.GoogleEventsController
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Contact
import org.eclipse.microprofile.openapi.annotations.info.Info
import org.eclipse.microprofile.openapi.annotations.info.License
import org.eclipse.microprofile.openapi.annotations.tags.Tag

class GoogleEventsControllerImpl(
    private val googleEventsService: GoogleEventsService
) : GoogleEventsController {

    override fun insertEvent(calendarId: String, googleEventsBody: GoogleEventsBody): GoogleEventsResponse {
        val request = googleEventsBody.toRequest(calendarId)
        return googleEventsService.insertEvent(request)
    }

}