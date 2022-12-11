package com.calendar.automation.entities.entity

import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import io.quarkus.security.jpa.RolesValue
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "event_table")
class Event : PanacheEntityBase() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    val id: Int? = null

    @Column(name = "summary")
    lateinit var summary: String

    @Column(name = "description")
    lateinit var description: String

    @Column(name = "html_link")
    lateinit var htmlLink: String

    @Column(name = "start_date_time")
    lateinit var startDateTime: LocalDateTime

    @Column(name = "end_date_time")
    lateinit var endDateTime: LocalDateTime

    @Column(name = "created_at")
    lateinit var createdAt: LocalDateTime

    @Column(name = "attendees")
    lateinit var attendes: String
}