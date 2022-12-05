package com.calendar.automation.usecases.service

import com.calendar.automation.entities.dto.Oauth2TokenResponse

interface GoogleOauthService {

    fun getToken(): Oauth2TokenResponse
}