package com.nikik0.applicationprocessor.dtos

import com.nikik0.applicationprocessor.entities.ApplicationEntity

data class SubmissionDto(
    val id: Long,
    val fullEnrolleeName: String,
    val fullBossName: String,
    val department: String,
    val currentPosition: String,
    val experienceMonth: Long,
    val achievements: String
){
    fun toEntity() =
        ApplicationEntity(
            id,
            fullEnrolleeName,
            fullBossName,
            department,
            currentPosition,
            experienceMonth,
            achievements,
            accepted = false
        )
}
