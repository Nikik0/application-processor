package com.nikik0.applicationprocessor.contollers

import com.nikik0.applicationprocessor.dtos.SubmissionDto
import com.nikik0.applicationprocessor.entities.ApplicationEntity
import com.nikik0.applicationprocessor.repositories.ApplicationRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/applications")
class ApplicationController(
    private val applicationRepository: ApplicationRepository
) {
    @PostMapping("/submit")
    suspend fun submit(@RequestBody submissionDto: SubmissionDto) =
        applicationRepository.save(submissionDto.toEntity())

    @GetMapping("/get/{id}")
    suspend fun get(@PathVariable id: Long) =
        applicationRepository.findById(id)

    @GetMapping("/get/all")
    suspend fun getAll() =
        applicationRepository.findAll()

    @PostMapping("/accept/{id}")
    suspend fun accept(@PathVariable id: Long) =
        applicationRepository.findById(id)?.let {
            it.accepted = true
            applicationRepository.save(it)
        }

}