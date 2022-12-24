package com.calendar.automation.web.controllers.impl

import com.calendar.automation.entities.dto.response.EncryptedPasswordResponse
import com.calendar.automation.entities.dto.response.UserResponse
import com.calendar.automation.entities.dto.response.toUserResponse
import com.calendar.automation.entities.enums.RoleEnum
import com.calendar.automation.entities.exception.CustomException
import com.calendar.automation.usecases.service.AuthorizationService
import com.calendar.automation.web.controllers.UserController
import io.quarkus.elytron.security.common.BcryptUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.regex.Pattern
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.BAD_REQUEST
import javax.ws.rs.core.SecurityContext

class UserControllerImpl(
    private val authorizationService: AuthorizationService
) : UserController {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    override fun me(securityContext: SecurityContext): UserResponse {
        logger.info("${this::class.simpleName} -> Starting to validate myself")

        return securityContext.run {
            authorizationService.validateRequest(
                securityContext = this,
                allowedRoles = RoleEnum.values().toList()
            )
            if (securityContext.userPrincipal != null) {
                this.toUserResponse()
            } else throw CustomException("The request must be authenticated", BAD_REQUEST)

        }.also {
            logger.info("${this::class.simpleName} -> Finishing to validate myself with success")
        }
    }

    override fun getEncryptedPassword(password: String?): EncryptedPasswordResponse {

        if (isValidPassword(password).not()) {
            throw CustomException(
                message = "The password must be at least 5 characters and special characters",
                status = BAD_REQUEST
            )
        }

        logger.info(
            "${this::class.simpleName} -> Starting to get an encrypted password [password: ${
                password!!.substring(
                    0,
                    5
                )
            }]"
        )

        return BcryptUtil.bcryptHash(password)
            .run {
                EncryptedPasswordResponse(
                    originalPassword = password,
                    encryptedPassword = this
                )
            }.also {
                logger.info("${this::class.simpleName} -> Finishing to encrypt a password with success")
            }
    }

    private fun isValidPassword(password: String?): Boolean {
        return if (password.isNullOrBlank()) {
            true
        } else {
            val pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE)
            val shouldPatternMatch = pattern.matcher(password).find()
            (password.length > 5 && shouldPatternMatch)
        }
    }

    companion object {
        const val REGEX = "[^a-z0-9 ]"
    }
}