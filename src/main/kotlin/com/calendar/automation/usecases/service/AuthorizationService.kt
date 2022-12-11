package com.calendar.automation.usecases.service

import com.calendar.automation.entities.enums.RoleEnum
import javax.ws.rs.core.SecurityContext

interface AuthorizationService {

    fun validateRequest(securityContext: SecurityContext, allowedRoles: List<RoleEnum>)
}