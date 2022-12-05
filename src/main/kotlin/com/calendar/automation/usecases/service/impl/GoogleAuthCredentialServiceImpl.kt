package com.calendar.automation.usecases.service.impl

import com.calendar.automation.client.Oauth2GoogleClient
import com.calendar.automation.entities.dto.Oauth2TokenRequest
import com.calendar.automation.entities.dto.Oauth2TokenResponse
import com.calendar.automation.usecases.service.GoogleOauthService
import io.quarkus.cache.CacheResult
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GoogleAuthCredentialServiceImpl(
    @RestClient private val oauth2GoogleClient: Oauth2GoogleClient,
    @ConfigProperty(name = "google.client.credentials.client-id") private val clientId: String,
    @ConfigProperty(name = "google.client.credentials.client-secret") private val clientSecret: String,
    @ConfigProperty(name = "google.client.refresh-token") private val refreshToken: String,
) : GoogleOauthService {

    @CacheResult(cacheName = "oauth-token-cache")
    override fun getToken(): Oauth2TokenResponse {
        return oauth2GoogleClient.getToken(
            Oauth2TokenRequest(
                clientId = clientId,
                clientSecret = clientSecret,
                refreshToken = refreshToken,
                grantType = GRANT_TYPE
            )
        )
    }

    companion object {
        private const val GRANT_TYPE = "refresh_token"
    }

}