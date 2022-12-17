package com.calendar.automation.entities.dto.response

data class EncryptedPasswordResponse(
    val originalPassword: String,
    val encryptedPassword: String
)
