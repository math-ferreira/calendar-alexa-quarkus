package com.calendar.automation.entities.entity

import io.quarkus.hibernate.orm.panache.PanacheEntity
import io.quarkus.security.jpa.RolesValue
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.Table
import javax.transaction.Transactional


@Entity
@Table(name = "role_table")
class Role : PanacheEntity() {

    @RolesValue
    @Column(name = "role_name")
    lateinit var roleName: String

    @Column(name = "description")
    lateinit var description: String

    @Column(name = "created_at")
    lateinit var createdAt: LocalDateTime

    @ManyToMany(mappedBy = "roles")
    lateinit var users: List<User>

    companion object {

        @Transactional
        fun add(roleName: String, description: String) =
            Role().apply {
                this.roleName = roleName
                this.description = description
                this.createdAt = LocalDateTime.now()
            }.apply {
                this.persist()
            }
    }
}