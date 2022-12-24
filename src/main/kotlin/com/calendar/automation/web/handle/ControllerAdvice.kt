package com.calendar.automation.web.handle

import com.calendar.automation.entities.dto.response.ErrorData
import com.calendar.automation.entities.dto.response.ErrorResponse
import com.calendar.automation.entities.enums.ErrorTypeEnum
import com.calendar.automation.entities.exception.CustomException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR
import javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ControllerAdvice : ExceptionMapper<Exception> {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun toResponse(exception: Exception): Response {

        logger.info("${this::class.simpleName} -> Starting to handle error [Class: ${exception::javaClass}]")

        return when (exception) {
            is CustomException -> handleCustomException(exception)
            else -> handleException(exception)
        }
    }

    private fun handleException(ex: Exception): Response {

        return createErrorResponse(
            exception = ex,
            errorType = ErrorTypeEnum.SERVER_ERROR
        ).run {
            Response.status(INTERNAL_SERVER_ERROR)
                .entity(this)
                .build()
        }.also {
            logger.info("${this::class.simpleName} -> Handle exception [status: ${it.status}]")
        }
    }

    private fun handleCustomException(ex: CustomException): Response {
        return createErrorResponse(
            exception = ex,
            errorType = getErrorTypeByStatus(ex.status)
        ).run {
            Response.status(ex.status)
                .entity(this)
                .build()
        }.also {
            logger.info("${this::class.simpleName} -> Handle custom exception [status: ${it.status}]")
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