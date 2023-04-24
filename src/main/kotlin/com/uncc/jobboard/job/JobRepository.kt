package com.uncc.jobboard.job

import com.uncc.jobboard.job.Job
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface JobRepository : JpaRepository<Job?, Int?> {

    fun findById(id: Int?):Optional<Job?>?
}