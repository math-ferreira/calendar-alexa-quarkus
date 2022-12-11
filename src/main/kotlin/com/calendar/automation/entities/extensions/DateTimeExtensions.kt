package com.calendar.automation.entities.extensions

import java.time.OffsetDateTime

fun String.toOffsetDateTime() = OffsetDateTime.parse( this)

fun String.toLocalDateTime() = OffsetDateTime.parse( this).toLocalDateTime()