package com.uncc.jobboard.job

import com.fasterxml.jackson.annotation.JsonIgnore
import com.uncc.jobboard.user.User
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
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
        var createdBy: String,
        var applicationLink: String,
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "user_id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        var user: User?,
        var bookmarkedBy: List<String>
)

