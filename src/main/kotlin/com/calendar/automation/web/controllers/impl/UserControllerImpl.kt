package com.calendar.automation.web.controllers.impl

import com.calendar.automation.entities.dto.old.UserResponse
import com.calendar.automation.entities.dto.old.toUserResponse
import com.calendar.automation.entities.enums.PermissionEnum
import com.calendar.automation.entities.enums.PermissionEnum.PUBLIC_ADMIN
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
                allowedRoles = PermissionEnum.values().toList()
            )
            this.toUserResponse()
        }
    }
}