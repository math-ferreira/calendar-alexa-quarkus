package com.calendar.automation.entities.dto.client.outh2google

import com.fasterxml.jackson.annotation.JsonProperty

data class Oauth2TokenClientResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("expires_in")
    val expiresIn: String,
    @JsonProperty("scope")
    val scope: String,
    @JsonProperty("token_type")
    val tokenType: String
) {
    override fun toString() =
        "[accessToken: ${accessToken.substring(0, 5)}###, expiresIn: $expiresIn, scope: ###, tokenType: $tokenType]"
}
