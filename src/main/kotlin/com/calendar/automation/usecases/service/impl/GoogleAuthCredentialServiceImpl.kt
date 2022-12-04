package com.calendar.automation.usecases.service.impl

import com.calendar.automation.entities.constants.GoogleServiceConstants
import com.calendar.automation.entities.constants.GoogleServiceConstants.APPLICATION_NAME
import com.calendar.automation.usecases.service.GoogleAuthCredentialService
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.io.File
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GoogleAuthCredentialServiceImpl(
    @ConfigProperty(name = "google.client.credentials.client-id") private val clientId: String,
    @ConfigProperty(name = "google.client.credentials.client-secret") private val clientSecret: String,
    @ConfigProperty(name = "google.client.credentials.auth-uri") private val authUri: String,
    @ConfigProperty(name = "google.client.credentials.redirect-uri") private val redirectUri: List<String>,
    @ConfigProperty(name = "google.client.credentials.token-uri") private val tokenUri: String,
    @ConfigProperty(name = "quarkus.http.port") private val port: Int
) : GoogleAuthCredentialService {

    override fun buildGoogleRequest(): Calendar {
        return Calendar.Builder(
            getHttpTransport(),
            JSON_FACTORY,
            getCredential()
        ).setApplicationName(APPLICATION_NAME).build()
    }

    override fun getCredential(): Credential {
        val details =
            GoogleClientSecrets.Details().setClientId(clientId).setClientSecret(clientSecret).setAuthUri(authUri)
                .setRedirectUris(redirectUri).setTokenUri(tokenUri)

        val clientSecrets = GoogleClientSecrets().setWeb(details)

        val flow =
            GoogleAuthorizationCodeFlow.Builder(getHttpTransport(), JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(
                    FileDataStoreFactory(File(GoogleServiceConstants.TOKENS_DIRECTORY_PATH))
                ).setAccessType("offline").build()

        val receiver = LocalServerReceiver.Builder().setHost("calendar-alexa.herokuapp.com").setPort(port).build()

        return AuthorizationCodeInstalledApp(flow, receiver).authorize(null)
    }

    private fun getHttpTransport(): NetHttpTransport = GoogleNetHttpTransport.newTrustedTransport()

    companion object {
        private val JSON_FACTORY: JsonFactory = GsonFactory.getDefaultInstance()
        private val SCOPES = CalendarScopes.all()
    }
}