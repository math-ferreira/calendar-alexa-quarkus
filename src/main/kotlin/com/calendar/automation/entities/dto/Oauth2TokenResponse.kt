package com.calendar.automation.entities.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Oauth2TokenResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("expires_in")
    val expiresIn: String,
    @JsonProperty("scope")
    val scope: String,
    @JsonProperty("token_type")
    val tokenType: String
)
