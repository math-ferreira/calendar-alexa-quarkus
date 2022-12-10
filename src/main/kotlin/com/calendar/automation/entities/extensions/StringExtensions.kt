package com.calendar.automation.entities.extensions

fun String.toAuthorization() = "Bearer $this"