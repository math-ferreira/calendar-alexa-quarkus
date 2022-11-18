package com.calendar.automation.entities.exception

import javax.ws.rs.core.Response

data class CustomException(
    override val message: String,
    val status: Response.Status
) : RuntimeException()