package com.calendar.automation.web.controllers

import com.calendar.automation.entities.constants.PermissionsConstants.GOOGLE_OAUTH_ROLE_NAME
import com.calendar.automation.entities.dto.client.outh2google.Oauth2TokenClientResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.annotation.security.RolesAllowed
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.SecurityContext

@Tag(
    name = "google-oauth",
    description = "Provide integration to google authentication"
)
@Path("/google-oauth")
interface GoogleOauthController {

    @GET
    @RolesAllowed(GOOGLE_OAUTH_ROLE_NAME)
    @Path("/token")
    fun getToken(
        @Context securityContext: SecurityContext
    ): Oauth2TokenClientResponse

}
