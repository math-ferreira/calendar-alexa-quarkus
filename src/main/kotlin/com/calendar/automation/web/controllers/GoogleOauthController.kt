package com.calendar.automation.web.controllers

import com.calendar.automation.entities.dto.Oauth2TokenResponse
import com.calendar.automation.entities.dto.old.GoogleCalendarListResponse
import com.calendar.automation.entities.dto.old.GoogleCalendarResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.annotation.security.RolesAllowed
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Context
import javax.ws.rs.core.SecurityContext

@Tag(
    name = "google-oauth",
    description = "Provide integration to google authentication"
)
@Path("/google-oauth")
interface GoogleOauthController {

    @GET
    @RolesAllowed("admin")
    @Path("/token")
    fun getToken(
        @Context securityContext: SecurityContext
    ): Oauth2TokenResponse

}
