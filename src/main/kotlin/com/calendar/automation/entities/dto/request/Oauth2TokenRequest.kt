package com.calendar.automation.entities.dto.request

data class Oauth2TokenRequest(
    val clientId: String,
    val clientSecret: String,
    val refreshToken: String,
    val grantType: String
)
