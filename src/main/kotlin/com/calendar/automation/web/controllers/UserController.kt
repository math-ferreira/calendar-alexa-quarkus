package com.calendar.automation.web.controllers

import com.calendar.automation.entities.constants.PermissionsConstants.PUBLIC_ROLE_NAME
import com.calendar.automation.entities.dto.response.EncryptedPasswordResponse
import com.calendar.automation.entities.dto.response.UserResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.annotation.security.RolesAllowed
import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.SecurityContext

@Tag(
    name = "users",
    description = "Services to check role and security features"
)
@Path("/users")
interface UserController {

    @GET
    @RolesAllowed(PUBLIC_ROLE_NAME)
    @Path("/me")
    fun me(
        @Context securityContext: SecurityContext
    ): UserResponse

    @GET
    @RolesAllowed(PUBLIC_ROLE_NAME)
    @Path("/encrypted-password")
    fun getEncryptedPassword(
        @HeaderParam(value = "password") password: String
    ): EncryptedPasswordResponse
}