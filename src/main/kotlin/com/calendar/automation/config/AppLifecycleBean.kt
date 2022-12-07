package com.calendar.automation.config

import com.calendar.automation.entities.repository.RoleRepository
import com.calendar.automation.entities.repository.UserRepository
import io.quarkus.runtime.StartupEvent
import javax.enterprise.event.Observes
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
class AppLifecycleBean(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun loadUsers(@Observes evt: StartupEvent?) {

        var publicRole = roleRepository.findByRoleName("public_role")
        var adminRole = roleRepository.findByRoleName("admin_role")

        if (publicRole == null && adminRole == null) {
            publicRole = roleRepository.save(
                roleName = "public_role",
                description = "available to public access"
            )
            adminRole = roleRepository.save(
                roleName = "admin_role",
                description = "specific features only available to admin access"
            )
        }

        val user = userRepository.findByUsername("user")
        val admin = userRepository.findByUsername("admin")

        if (user == null && admin == null) {
            userRepository.save("user", "user", listOf(publicRole!!))
            userRepository.save("admin", "admin", listOf(adminRole!!))
        }
    }

}