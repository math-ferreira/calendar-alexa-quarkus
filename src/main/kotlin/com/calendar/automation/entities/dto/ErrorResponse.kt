package com.calendar.automation.entities.dto

import com.calendar.automation.entities.enums.ErrorTypeEnum

data class ErrorResponse(
    val message: String?,
    val errorData: ErrorData
)

data class ErrorData(
    val description: String,
    val type: ErrorTypeEnum
)
