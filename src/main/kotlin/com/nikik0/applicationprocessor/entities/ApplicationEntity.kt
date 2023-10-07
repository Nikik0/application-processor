package com.nikik0.applicationprocessor.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("applications")
data class ApplicationEntity(
    @Id
    val id: Long,
    @Column("full_enrollee_name")
    val fullEnrolleeName: String,
    @Column("full_boss_name")
    val fullBossName: String,
    val department: String,
    @Column("current_position")
    val currentPosition: String,
    @Column("experience_month")
    val experienceMonth: Long,
    val achievements: String,
    var accepted: Boolean
)
