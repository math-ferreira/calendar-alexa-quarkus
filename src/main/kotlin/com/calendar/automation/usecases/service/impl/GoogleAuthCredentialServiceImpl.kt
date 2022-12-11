package com.calendar.automation.usecases.service.impl

import com.calendar.automation.client.Oauth2GoogleClient
import com.calendar.automation.entities.dto.request.Oauth2TokenRequest
import com.calendar.automation.entities.dto.client.outh2google.Oauth2TokenClientResponse
import com.calendar.automation.usecases.service.GoogleOauthService
import io.quarkus.cache.CacheResult
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GoogleAuthCredentialServiceImpl(
    @RestClient private val oauth2GoogleClient: Oauth2GoogleClient,
    @ConfigProperty(name = "google.client.credentials.client-id") private val clientId: String,
    @ConfigProperty(name = "google.client.credentials.client-secret") private val clientSecret: String,
    @ConfigProperty(name = "google.client.refresh-token") private val refreshToken: String,
) : GoogleOauthService {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @CacheResult(cacheName = "oauth-token-cache")
    override fun getToken(): Oauth2TokenClientResponse {

        logger.info("${this::class.simpleName} -> Starting to get token form Google services")

        return oauth2GoogleClient.getToken(
            Oauth2TokenRequest(
                clientId = clientId,
                clientSecret = clientSecret,
                refreshToken = refreshToken,
                grantType = GRANT_TYPE
            )
        ).also {
            logger.info("${this::class.simpleName} -> Token from Google generated with success: $it")
        }
    }

    companion object {
        private const val GRANT_TYPE = "refresh_token"
    }

}