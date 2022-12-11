package com.calendar.automation.usecases.service.impl

import com.calendar.automation.entities.enums.RoleEnum
import com.calendar.automation.entities.exception.CustomException
import com.calendar.automation.usecases.service.AuthorizationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.Response
import javax.ws.rs.core.SecurityContext

@ApplicationScoped
class AuthorizationServiceImpl : AuthorizationService {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun validateRequest(securityContext: SecurityContext, allowedRoles: List<RoleEnum>) {

        logger.info("${this::class.simpleName} -> Starting to validate request with basic auth [userPrincipal: ${getUserPrincipal(securityContext)}]")

        validateAuthenticationScheme(securityContext)

        validateRolesByUser(
            securityContext = securityContext,
            roleList = allowedRoles
        )

        logger.info("${this::class.simpleName} -> Allowed access to the user [userPrincipal: ${getUserPrincipal(securityContext)}]")
    }

    private fun validateAuthenticationScheme(securityContext: SecurityContext) {
        if (securityContext.authenticationScheme.isNullOrBlank()) {
            logger.info("${this::class.simpleName} -> Authentication scheme is invalid [userPrincipal: ${getUserPrincipal(securityContext)}]")
            throw CustomException(
                message = "Request unauthorized, please set the credentials and try again",
                status = Response.Status.UNAUTHORIZED
            )
        }
    }

    private fun validateRolesByUser(
        securityContext: SecurityContext,
        roleList: List<RoleEnum>
    ) {

        roleList.firstOrNull {
            securityContext.isUserInRole(it.roleName)
        } ?: throw CustomException(
            message = "Its a valid authentication, but the access is forbidden",
            status = Response.Status.FORBIDDEN
        ).also {
            logger.info("${this::class.simpleName} -> Access Forbidden to the user [userPrincipal: ${getUserPrincipal(securityContext)}]")
        }
    }

    private fun getUserPrincipal(securityContext: SecurityContext) = securityContext.userPrincipal.name

}