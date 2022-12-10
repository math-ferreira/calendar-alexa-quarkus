package com.calendar.automation.usecases.service

import com.calendar.automation.entities.dto.request.EventsRequest
import com.calendar.automation.entities.dto.response.EventsResponse

interface GoogleEventsService {

    fun insertEvent(googleEventsRequest: EventsRequest): EventsResponse
}