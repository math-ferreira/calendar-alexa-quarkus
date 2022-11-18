package com.calendar.automation.entities.enums

enum class ErrorTypeEnum(val description: String) {
    CLIENT_ERROR("Error to process your request. Please check that your details are correct and try again"),
    SERVER_ERROR("Unexpected server error. Please try again in a few moments, if the problem persists, contact support")
}