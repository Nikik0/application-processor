package com.nikik0.applicationprocessor.repositories

import com.nikik0.applicationprocessor.entities.ApplicationEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ApplicationRepository: CoroutineCrudRepository<ApplicationEntity, Long> {
}