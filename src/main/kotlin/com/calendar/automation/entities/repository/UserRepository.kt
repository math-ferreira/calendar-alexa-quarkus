package com.calendar.automation.entities.repository

import com.calendar.automation.entities.entity.Role
import com.calendar.automation.entities.entity.User
import io.quarkus.elytron.security.common.BcryptUtil
import io.quarkus.hibernate.orm.panache.PanacheRepository
import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserRepository : PanacheRepository<User> {

    fun save(username: String, password: String, role: List<Role>) =
        User().apply {
            this.username = username
            this.password = BcryptUtil.bcryptHash(password)
            this.roles = role
            this.createdAt = LocalDateTime.now()
        }.apply {
            this.persist()
        }

    fun findByUsername(username: String): User? =
        find(USER_NAME, username)?.firstResult()

    fun deleteByUsername(username: String) =
        delete(USER_NAME, username)

    companion object {
        const val USER_NAME = "username"
    }
}