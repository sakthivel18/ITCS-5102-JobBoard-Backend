package com.uncc.jobboard.jobPosting

import org.springframework.data.annotation.CreatedBy
import java.time.LocalDate

data class JobRequest(
        val title: String,
        var companyName: String,
        var description: String,
        var location: String,
        var experience: String,
        var jobType: String,
        var workMode:String,
        var addedDate: LocalDate?,
        var applicantsApplied: Int,
        var createdBy: String,
        var applicationLink: String
)