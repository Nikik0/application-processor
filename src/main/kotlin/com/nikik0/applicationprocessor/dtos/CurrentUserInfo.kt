package com.nikik0.applicationprocessor.dtos

data class CurrentUserInfo(
    val id: Long,
    val username: String,
    val role: String,
    val enabled: Boolean
)
