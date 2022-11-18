package com.calendar.automation.usecases.service

import com.calendar.automation.entities.dto.GoogleEventsRequest
import com.calendar.automation.entities.dto.GoogleEventsResponse

interface GoogleEventsService {

    fun insertEvent(googleEventsRequest: GoogleEventsRequest): GoogleEventsResponse
}