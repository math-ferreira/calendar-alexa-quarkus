package com.calendar.automation.config

import com.calendar.automation.entities.entity.User
import io.quarkus.runtime.StartupEvent
import javax.enterprise.event.Observes
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
class StartupConfig() {

    @Transactional
    fun loadUsers(@Observes evt: StartupEvent?) {
        User.add("admin", "admin", "admin")
        User.add("user", "user", "user")
    }
}