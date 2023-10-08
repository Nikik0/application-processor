package com.nikik0.applicationprocessor.contollers

import com.nikik0.applicationprocessor.dtos.SubmissionDto
import com.nikik0.applicationprocessor.entities.ApplicationEntity
import com.nikik0.applicationprocessor.repositories.ApplicationRepository
import com.nikik0.applicationprocessor.services.ApplicationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.http.HttpHeaders
import java.net.http.HttpResponse

@RestController
@RequestMapping("/api/v1/applications")
class ApplicationController(
    private val applicationService: ApplicationService
) {
    @PostMapping("/submit")
    suspend fun submit(@RequestBody submissionDto: SubmissionDto, @RequestHeader("Authorization") auth: String) =
        applicationService.save(submissionDto, auth)

    @GetMapping("/get/{id}")
    suspend fun get(@PathVariable id: Long, @RequestHeader("Authorization") auth: String) =
        applicationService.getSingle(id, auth)

    @GetMapping("/get/all")
    suspend fun getAll(@RequestHeader("Authorization") auth: String) =
        applicationService.getAll(auth)

    @PostMapping("/accept/{id}")
    suspend fun accept(@PathVariable id: Long, @RequestHeader("Authorization") auth: String) =
        applicationService.accept(id, auth)
}