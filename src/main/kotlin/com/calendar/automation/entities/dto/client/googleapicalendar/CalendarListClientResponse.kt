package com.calendar.automation.entities.dto.client.googleapicalendar

import com.fasterxml.jackson.annotation.JsonProperty

data class CalendarListClientResponse(
    @JsonProperty("items")
    val items: List<Item>
)


data class Item(
    @JsonProperty("kind")
    val kind: String,
    @JsonProperty("primary")
    val primary: Boolean,
    @JsonProperty("colorId")
    val colorId: String,
    @JsonProperty("summary")
    val summary: String,
    @JsonProperty("timeZone")
    val timeZone: String,
    @JsonProperty("id")
    val id: String,
    @JsonProperty("accessRole")
    val accessRole: String
)