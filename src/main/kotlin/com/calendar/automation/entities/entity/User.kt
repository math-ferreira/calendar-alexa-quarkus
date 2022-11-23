package com.calendar.automation.entities.entity

import io.quarkus.elytron.security.common.BcryptUtil
import io.quarkus.hibernate.orm.panache.PanacheEntity
import io.quarkus.security.jpa.Password
import io.quarkus.security.jpa.Roles
import io.quarkus.security.jpa.UserDefinition
import io.quarkus.security.jpa.Username
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.transaction.Transactional


@Entity
@Table(name = "user_table")
@UserDefinition
class User : PanacheEntity() {

    @Username
    lateinit var username: String

    @Password
    lateinit var password: String

    @Roles
    lateinit var role: String

    @Column(name = "created_at")
    lateinit var createdAt: LocalDateTime

    companion object {

        @Transactional
        fun add(username: String, password: String, role: String) =
            User().apply {
                this.username = username
                this.password = BcryptUtil.bcryptHash(password)
                this.role = role
                this.createdAt = LocalDateTime.now()
            }.run {
                this.persist()
            }
    }
}