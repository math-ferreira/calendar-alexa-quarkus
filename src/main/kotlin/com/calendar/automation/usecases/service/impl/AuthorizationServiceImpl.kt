package com.calendar.automation.usecases.service.impl

import com.calendar.automation.entities.enums.PermissionEnum
import com.calendar.automation.entities.exception.CustomException
import com.calendar.automation.usecases.service.AuthorizationService
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.Response
import javax.ws.rs.core.SecurityContext

@ApplicationScoped
class AuthorizationServiceImpl: AuthorizationService {

    override fun validateRequest(securityContext: SecurityContext, allowedRoles: List<PermissionEnum>) {

        if (securityContext.authenticationScheme.isNullOrBlank()) {
            throw CustomException(
                message = "request unauthorized, please set the credentials and try again",
                status = Response.Status.UNAUTHORIZED
            )
        }

        allowedRoles.firstOrNull {
            securityContext.isUserInRole(it.roleName)
        } ?: throw CustomException(
            message = "Its a valid authentication, but the access is forbidden",
            status = Response.Status.FORBIDDEN
        )

    }

}