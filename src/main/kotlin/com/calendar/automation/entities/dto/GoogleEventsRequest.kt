package com.calendar.automation.entities.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleEventsRequest(
    val googleEventsQueries: GoogleEventsQueries,
    val googleEventsBody: GoogleEventsBody,
)

fun GoogleEventsBody.toRequest(calendarId: String) =
    GoogleEventsRequest(
        googleEventsBody = this,
        googleEventsQueries = GoogleEventsQueries(
            calendarId = calendarId
        )
    )


data class GoogleEventsQueries(
    val calendarId: String
)

data class GoogleEventsBody(
    @JsonProperty("send_updates")
    val sendUpdates: Boolean,
    @JsonProperty("attendees")
    val attendees: List<GoogleEventsAttendees>,
    @JsonProperty("color_id")
    val colorId: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("start_date")
    val startDate: String,
    @JsonProperty("end_date")
    val endDate: String,
    @JsonProperty("location")
    val location: String,
    @JsonProperty("summary")
    val summary: String,
    @JsonProperty("reminders")
    val reminders: GoogleEventsReminders?
)

data class GoogleEventsAttendees(
    @JsonProperty("display_name")
    val displayName: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("is_optional")
    val isOptional: Boolean,
    @JsonProperty("response_status")
    val responseStatus: String
)

data class GoogleEventsReminders(
    @JsonProperty("method")
    val method: String,
    @JsonProperty("trigger_minutes")
    val triggerMinutes: Int,
)
