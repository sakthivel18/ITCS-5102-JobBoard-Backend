package com.uncc.jobboard.job

import com.uncc.jobboard.user.User
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "jobs")
data class Job(
        @Id
        @GeneratedValue
        val jobId: Int? = null,
        var title: String,
        var companyName: String,
        var description: String,
        var location: String,
        var experience: String,
        var jobType: String,
        var workMode: String,
        var addedDate: LocalDate,
        var applicantsApplied: Int? = null,
        var isCreated: Boolean,
        var applicationLink: String,
        @ManyToOne
        @JoinColumn(name = "user_id")
        var user: User?

)

