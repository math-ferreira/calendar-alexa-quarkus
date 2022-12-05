package com.calendar.automation.usecases.service

import com.calendar.automation.entities.dto.old.GoogleEventsRequest
import com.calendar.automation.entities.dto.old.GoogleEventsResponse

interface GoogleEventsService {

    fun insertEvent(googleEventsRequest: GoogleEventsRequest): GoogleEventsResponse
}