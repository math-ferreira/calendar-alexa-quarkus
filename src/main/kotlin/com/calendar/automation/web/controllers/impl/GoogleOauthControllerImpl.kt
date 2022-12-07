package com.calendar.automation.web.controllers.impl

import com.calendar.automation.entities.dto.client.outh2google.Oauth2TokenClientResponse
import com.calendar.automation.entities.enums.PermissionEnum.GOOGLE_OAUTH
import com.calendar.automation.usecases.service.AuthorizationService
import com.calendar.automation.usecases.service.GoogleOauthService
import com.calendar.automation.web.controllers.GoogleOauthController
import javax.ws.rs.core.SecurityContext

class GoogleOauthControllerImpl(
    private val googleOauthService: GoogleOauthService,
    private val authorizationService: AuthorizationService
) : GoogleOauthController {

    override fun getToken(securityContext: SecurityContext): Oauth2TokenClientResponse {
        authorizationService.validateRequest(
            securityContext = securityContext,
            allowedRoles = listOf(GOOGLE_OAUTH)
        )
        return googleOauthService.getToken()
    }

}