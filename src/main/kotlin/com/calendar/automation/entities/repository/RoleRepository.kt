package com.calendar.automation.entities.repository

import com.calendar.automation.entities.entity.Role
import io.quarkus.hibernate.orm.panache.PanacheRepository
import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class RoleRepository : PanacheRepository<Role> {

    fun save(roleName: String, description: String) =
        Role().apply {
            this.roleName = roleName
            this.description = description
            this.createdAt = LocalDateTime.now()
        }.apply {
            this.persist()
        }

    fun findByRoleName(roleName: String): Role? =
        find(ROLE_NAME, roleName)?.firstResult()

    fun deleteByRoleName(roleName: String) =
        delete(ROLE_NAME, roleName)


    companion object {
        const val ROLE_NAME = "role_name"
    }
}