
package com.calendar.automation.client

import com.calendar.automation.entities.dto.Oauth2TokenRequest
import com.calendar.automation.entities.dto.Oauth2TokenResponse
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.Encoded
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@RegisterRestClient(configKey = "oauth2-google")
interface Oauth2GoogleClient {

    @POST
    @Path(value = "/token")
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    fun getToken(
        @RequestBody @Encoded oauthTokenRequest: Oauth2TokenRequest
    ): Oauth2TokenResponse
}
