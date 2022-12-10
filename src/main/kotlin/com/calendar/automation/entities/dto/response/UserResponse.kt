package com.calendar.automation.entities.dto.response

import javax.ws.rs.core.SecurityContext

data class UserResponse(
    val userPrincipalName: String,
    val authenticationScheme: String
)

fun SecurityContext.toUserResponse() =
    UserResponse(
        userPrincipalName = userPrincipal.name,
        authenticationScheme = authenticationScheme
    )
