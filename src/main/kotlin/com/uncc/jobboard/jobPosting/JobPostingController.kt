package com.uncc.jobboard.jobPosting

import com.uncc.jobboard.job.Job
import org.springframework.web.bind.annotation.*

@CrossOrigin(
        origins = ["http://localhost:3000"],
        maxAge = 3600L,
        allowCredentials = "true",
        allowedHeaders = ["Authorization", "Cache-Control", "Content-Type"],
        exposedHeaders = ["X-Get-Header"]
)
@RestController
@RequestMapping("/api/v1/jobs")
class JobPostingController(private val jobService: JobPostingService) {
    @GetMapping
    fun getAllJobs(): MutableList<Job?> {
        return jobService.getAllJobs()
    }

    @GetMapping("/{id}")
    fun getJobById(@PathVariable id: Int):Job?{
        return jobService.getJobById(id)
    }

    @PostMapping("/create")
    fun createJob(@RequestBody request: JobRequest): Job? {
        return jobService.createJob(request)
    }

    @PutMapping("/update/{id}")
    fun updateJobById(@PathVariable id: Int, @RequestBody request: JobRequest): Job? {
        return jobService.updateJobById(id, request)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteJobById(@PathVariable id: Int): Boolean {
        return jobService.deleteJobById(id)
    }



}