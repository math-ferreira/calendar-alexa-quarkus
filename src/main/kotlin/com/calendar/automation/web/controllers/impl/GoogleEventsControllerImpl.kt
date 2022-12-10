package com.calendar.automation.web.controllers.impl

import com.calendar.automation.entities.dto.request.EventsRequestBody
import com.calendar.automation.entities.dto.response.EventsResponse
import com.calendar.automation.entities.dto.request.toRequest
import com.calendar.automation.entities.enums.PermissionEnum
import com.calendar.automation.usecases.service.AuthorizationService
import com.calendar.automation.usecases.service.GoogleEventsService
import com.calendar.automation.web.controllers.GoogleEventsController
import javax.ws.rs.core.SecurityContext

class GoogleEventsControllerImpl(
    private val authorizationService: AuthorizationService,
    private val googleEventsService: GoogleEventsService
) : GoogleEventsController {

    override fun insertEvent(
        securityContext: SecurityContext,
        calendarId: String,
        googleEventsBody: EventsRequestBody
    ): EventsResponse {

        authorizationService.validateRequest(
            securityContext = securityContext,
            allowedRoles = listOf(PermissionEnum.GOOGLE_EVENTS)
        )

        return googleEventsBody.toRequest(calendarId)
            .run {
                googleEventsService.insertEvent(this)
            }

    }

}