package com.calendar.automation.config

import com.calendar.automation.entities.entity.Role
import com.calendar.automation.entities.entity.User
import io.quarkus.runtime.ShutdownEvent
import io.quarkus.runtime.StartupEvent
import javax.enterprise.event.Observes
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
class AppLifecycleBean() {

    @Transactional
    fun loadUsers(@Observes evt: StartupEvent?) {
        val publicRole = Role.add("public_role", description = "available to public access")
        val adminRole = Role.add("admin_role", description = "specific features only available to admin access")

        User.add("user", "user", listOf(publicRole))
        User.add("admin", "admin", listOf(publicRole, adminRole))

    }

}