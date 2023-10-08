package com.nikik0.applicationprocessor.services

import com.nikik0.applicationprocessor.dtos.CurrentUserInfo
import com.nikik0.applicationprocessor.dtos.SubmissionDto
import com.nikik0.applicationprocessor.entities.ApplicationEntity
import com.nikik0.applicationprocessor.repositories.ApplicationRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlow

@Service
class ApplicationService (
    private val applicationRepository: ApplicationRepository
        ){

    private val baseurl = "http://localhost:8080/api/v1/auth/info"

    private val securityProvider = WebClient.builder().baseUrl(baseurl).build()

    private suspend fun callToCheckCred(authentication: String, acceptedRoles: List<String>): Boolean {
        val currentUserInfo =
            securityProvider.get().header(HttpHeaders.AUTHORIZATION, authentication).retrieve().bodyToFlow<CurrentUserInfo>()
                .toList().first()
        return acceptedRoles.contains(currentUserInfo.role.lowercase())
    }

    suspend fun accept(id: Long, authentication: String) =
        if (callToCheckCred(authentication, listOf("professor", "mentor", "admin")))
            applicationRepository.findById(id)?.let {
                it.accepted = true
                applicationRepository.save(it)
            } else null

    suspend fun getAll(authentication: String) =
        if (callToCheckCred(authentication, listOf("enrollee", "student","professor", "mentor", "admin")))
            applicationRepository.findAll()
        else null

    suspend fun save(submissionDto: SubmissionDto, auth: String) =
        if (callToCheckCred(auth, listOf("enrollee", "student","professor", "mentor", "admin")))
            applicationRepository.save(submissionDto.toEntity())
        else null

    suspend fun getSingle(id: Long, auth: String) =
        if (callToCheckCred(auth, listOf("enrollee", "student","professor", "mentor", "admin")))
            applicationRepository.findById(id)
        else null
}