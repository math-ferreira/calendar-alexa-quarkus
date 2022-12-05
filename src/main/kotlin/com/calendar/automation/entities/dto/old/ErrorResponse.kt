package com.calendar.automation.entities.dto.old

import com.calendar.automation.entities.enums.ErrorTypeEnum

data class ErrorResponse(
    val message: String?,
    val errorData: ErrorData
)

data class ErrorData(
    val description: String,
    val type: ErrorTypeEnum
)
