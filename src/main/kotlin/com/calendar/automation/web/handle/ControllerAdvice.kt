package com.calendar.automation.web.handle

import com.calendar.automation.entities.dto.ErrorData
import com.calendar.automation.entities.dto.ErrorResponse
import com.calendar.automation.entities.enums.ErrorTypeEnum
import com.calendar.automation.entities.exception.CustomException
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR
import javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ControllerAdvice : ExceptionMapper<Exception> {

    override fun toResponse(exception: Exception): Response {
        return when (exception) {
            is CustomException -> handleGlobalException(exception)
            else -> handleException(exception)
        }
    }

    private fun handleException(ex: Exception): Response {
        return createErrorResponse(ex, ErrorTypeEnum.SERVER_ERROR)
            .run {
                Response.status(INTERNAL_SERVER_ERROR)
                    .entity(this)
                    .build()
            }
    }

    private fun handleGlobalException(ex: CustomException): Response {
        return getErrorTypeByStatus(ex.status)
            .let {
                createErrorResponse(ex, it)
            }
            .run {
                Response.status(ex.status)
                    .entity(this)
                    .build()
            }

    }


    private fun getErrorTypeByStatus(status: Response.Status): ErrorTypeEnum {
        return when (status.family) {
            CLIENT_ERROR -> ErrorTypeEnum.CLIENT_ERROR
            else -> ErrorTypeEnum.SERVER_ERROR
        }
    }

    private fun createErrorResponse(
        exception: Exception,
        errorType: ErrorTypeEnum
    ) = ErrorResponse(
        message = exception.message,
        errorData = ErrorData(
            description = errorType.description,
            type = errorType
        )
    )
}