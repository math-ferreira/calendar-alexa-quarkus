package com.calendar.automation.entities.dto.client.googleapicalendar

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleInsertEventClientResponse(
    @JsonProperty("kind")
    val kind: String,
    @JsonProperty("summary")
    val summary: String,
    @JsonProperty("description")
    val description: String? = null,
    @JsonProperty("eventType")
    val eventType: String,
    @JsonProperty("created")
    val created: String,
    @JsonProperty("colorId")
    val colorId: String,
    @JsonProperty("location")
    val location: String,
    @JsonProperty("htmlLink")
    val htmlLink: String,
    @JsonProperty("creator")
    val creator: CreatorClientResponse,
    @JsonProperty("start")
    val eventStartDate: DateTimeClientResponse,
    @JsonProperty("end")
    val eventEndDate: DateTimeClientResponse,
    @JsonProperty("attendees")
    val attendees: List<AttendeesClientResponse>? = emptyList(),
)

data class CreatorClientResponse(
    @JsonProperty("id")
    val id: String? = null,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("displayName")
    val displayName: String? = null
)

data class DateTimeClientResponse(
    @JsonProperty("date")
    val date: String? = null,
    @JsonProperty("dateTime")
    val dateTime: String,
    @JsonProperty("timeZone")
    val timeZone: String? = null,
)

data class AttendeesClientResponse(
    @JsonProperty("id")
    val id: String? = null,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("displayName")
    val displayName: String,
) {
    override fun toString() = "[name: $displayName, email: $email]"
}