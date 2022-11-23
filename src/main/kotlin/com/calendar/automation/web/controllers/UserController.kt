package com.calendar.automation.web.controllers

import com.calendar.automation.entities.dto.UserResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.annotation.security.RolesAllowed
import javax.ws.rs.GET
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
    @RolesAllowed("user")
    @Path("/me")
    fun me(
        @Context securityContext: SecurityContext
    ): UserResponse
}