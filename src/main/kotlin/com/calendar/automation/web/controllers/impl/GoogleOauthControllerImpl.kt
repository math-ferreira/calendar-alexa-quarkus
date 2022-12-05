package com.calendar.automation.web.controllers.impl

import com.calendar.automation.entities.dto.Oauth2TokenResponse
import com.calendar.automation.entities.enums.PermissionEnum
import com.calendar.automation.entities.exception.CustomException
import com.calendar.automation.usecases.service.AuthorizationService
import com.calendar.automation.usecases.service.GoogleOauthService
import com.calendar.automation.web.controllers.GoogleOauthController
import javax.ws.rs.core.Response
import javax.ws.rs.core.SecurityContext

class GoogleOauthControllerImpl(
    private val googleOauthService: GoogleOauthService,
    private val authorizationService: AuthorizationService
) : GoogleOauthController {

    override fun getToken(securityContext: SecurityContext): Oauth2TokenResponse {
        authorizationService.validateRequest(securityContext, listOf(PermissionEnum.ROLE_ADMIN))
        return googleOauthService.getToken()
    }

}