package com.calendar.automation.entities.dto.client.googleapicalendar

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleCalendarClientResponse(
    @JsonProperty("kind")
    val kind: String,
    @JsonProperty("summary")
    val summary: String,
    @JsonProperty("etag")
    val etag: String,
    @JsonProperty("timeZone")
    val timeZone: String,
    @JsonProperty("id")
    val id: String
)