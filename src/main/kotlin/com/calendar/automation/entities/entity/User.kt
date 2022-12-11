package com.calendar.automation.entities.entity

import io.quarkus.elytron.security.common.BcryptUtil
import io.quarkus.hibernate.orm.panache.PanacheEntity
import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import io.quarkus.security.jpa.Password
import io.quarkus.security.jpa.Roles
import io.quarkus.security.jpa.UserDefinition
import io.quarkus.security.jpa.Username
import java.time.LocalDateTime
import javax.persistence.*
import javax.transaction.Transactional


@Entity
@Table(name = "user_table")
@UserDefinition
class User : PanacheEntityBase() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    val id: Int? = null

    @Username
    lateinit var username: String

    @Password
    lateinit var password: String

    @ManyToMany
    @JoinTable(
        name = "user_role_table",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    @Roles
    lateinit var roles: List<Role>

    @Column(name = "created_at")
    lateinit var createdAt: LocalDateTime

}