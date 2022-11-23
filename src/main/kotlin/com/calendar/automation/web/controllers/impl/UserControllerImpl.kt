package com.calendar.automation.web.controllers.impl

import com.calendar.automation.entities.dto.UserResponse
import com.calendar.automation.entities.dto.toUserResponse
import com.calendar.automation.web.controllers.UserController
import javax.ws.rs.core.SecurityContext

class UserControllerImpl: UserController {

    override fun me(securityContext: SecurityContext): UserResponse {
        return securityContext.toUserResponse()
    }
}