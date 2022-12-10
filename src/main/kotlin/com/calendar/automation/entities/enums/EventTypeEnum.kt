package com.calendar.automation.entities.enums

enum class EventTypeEnum(val value: String) {

    DEFAULT("default"),
    OUT_OF_OFFICE("outOfOffice"),
    FOCUS_TIME("focusTime");

    companion object {

        fun getValue(eventTypeEnum: EventTypeEnum) =
            values().first { eventTypeEnum == it }.value
    }

}