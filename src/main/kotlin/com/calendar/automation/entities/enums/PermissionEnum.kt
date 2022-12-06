package com.calendar.automation.entities.enums

import com.calendar.automation.entities.constants.PermissionsConstants.ADMIN_ROLE_NAME
import com.calendar.automation.entities.constants.PermissionsConstants.GOOGLE_OAUTH_ROLE_NAME
import com.calendar.automation.entities.constants.PermissionsConstants.PUBLIC_ROLE_NAME

enum class PermissionEnum(val roleName: String) {

    PUBLIC_ADMIN(PUBLIC_ROLE_NAME),
    ROLE_ADMIN(ADMIN_ROLE_NAME),
    GOOGLE_OAUTH(GOOGLE_OAUTH_ROLE_NAME)
}