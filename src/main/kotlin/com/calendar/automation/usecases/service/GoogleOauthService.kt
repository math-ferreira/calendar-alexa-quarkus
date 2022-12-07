package com.calendar.automation.usecases.service

import com.calendar.automation.entities.dto.client.outh2google.Oauth2TokenClientResponse

interface GoogleOauthService {

    fun getToken(): Oauth2TokenClientResponse
}