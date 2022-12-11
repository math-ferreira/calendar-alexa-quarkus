package com.calendar.automation.web.controllers.impl

import com.calendar.automation.entities.dto.response.UserResponse
import com.calendar.automation.entities.dto.response.toUserResponse
import com.calendar.automation.entities.enums.RoleEnum
import com.calendar.automation.usecases.service.AuthorizationService
import com.calendar.automation.web.controllers.UserController
import javax.ws.rs.core.SecurityContext

class UserControllerImpl(
    private val authorizationService: AuthorizationService
) : UserController {

    override fun me(securityContext: SecurityContext): UserResponse {
        return securityContext.run {
            authorizationService.validateRequest(
                securityContext = this,
                allowedRoles = RoleEnum.values().toList()
            )
            this.toUserResponse()
        }
    }
}