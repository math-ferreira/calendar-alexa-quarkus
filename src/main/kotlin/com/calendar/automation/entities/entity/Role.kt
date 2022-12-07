package com.calendar.automation.entities.entity

import io.quarkus.hibernate.orm.panache.PanacheEntity
import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import io.quarkus.security.jpa.RolesValue
import java.time.LocalDateTime
import javax.persistence.*
import javax.transaction.Transactional


@Entity
@Table(name = "role_table")
class Role : PanacheEntityBase() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(
        columnDefinition = "SERIAL"
    )
    val id: Int? = null

    @RolesValue
    @Column(name = "role_name")
    lateinit var roleName: String

    @Column(name = "description")
    lateinit var description: String

    @Column(name = "created_at")
    lateinit var createdAt: LocalDateTime

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    lateinit var users: List<User>
}