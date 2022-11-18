package com.calendar.automation.usecases.service

import com.google.api.client.auth.oauth2.Credential
import com.google.api.services.calendar.Calendar

interface GoogleAuthCredentialService {

    fun buildGoogleRequest(): Calendar

    fun getCredential(): Credential
}