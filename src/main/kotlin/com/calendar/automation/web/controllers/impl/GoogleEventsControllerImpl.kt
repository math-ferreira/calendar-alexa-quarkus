package com.calendar.automation.web.controllers.impl

import com.calendar.automation.entities.dto.old.GoogleEventsBody
import com.calendar.automation.entities.dto.old.GoogleEventsResponse
import com.calendar.automation.entities.dto.old.toRequest
import com.calendar.automation.usecases.service.GoogleEventsService
import com.calendar.automation.web.controllers.GoogleEventsController

class GoogleEventsControllerImpl(
    private val googleEventsService: GoogleEventsService
) : GoogleEventsController {

    override fun insertEvent(calendarId: String, googleEventsBody: GoogleEventsBody): GoogleEventsResponse {
        val request = googleEventsBody.toRequest(calendarId)
        return googleEventsService.insertEvent(request)
    }

}