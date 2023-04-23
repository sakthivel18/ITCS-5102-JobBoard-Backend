package com.uncc.jobboard.jobPosting

import com.uncc.jobboard.auth.AuthenticationController
import com.uncc.jobboard.config.JwtService
import com.uncc.jobboard.job.Job
import com.uncc.jobboard.user.User
import com.uncc.jobboard.user.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.*


//@CrossOrigin(
//        origins = ["http://localhost:3000"],
//        maxAge = 3600L,
//        allowCredentials = "true",
//        allowedHeaders = ["Authorization", "Cache-Control", "Content-Type"],
//        exposedHeaders = ["X-Get-Header"]
//)
@RestController
@RequestMapping("/api/v1/jobs")
class JobPostingController(
    private val jobService: JobPostingService,
    private val jwtService: JwtService,
    private val userRepository: UserRepository
    ) {
    @GetMapping("/")
    fun getAllJobs(@RequestHeader("Authorization") token: String): ResponseEntity<MutableList<Job?>> {
        logger.info(token)
        val response = jobService.getAllJobs()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/user")
    fun getAllJobsOfUser(@RequestHeader("Authorization") token: String): MutableList<Job?>? {
        val email = jwtService.extractUsername(token.substring(7))
        val user = findUserId(email) ?: throw Exception("User not found")
        return jobService.getAllJobsOfUser(user)
    }

    @GetMapping("/companies")
    fun getCompanyNames() : MutableList<String?>? {
        return jobService.getAllJobs().stream()
            .map { job -> job?.companyName }
            .distinct()
            .toList()
    }

    @GetMapping("/{id}")
    fun getJobById(@PathVariable id: Int):Job?{
        return jobService.getJobById(id)
    }

    @PostMapping("/create")
    fun createJob(@RequestHeader("Authorization") token : String, @RequestBody request: JobRequest): Job? {
        val email = jwtService.extractUsername(token.substring(7))
        val user = findUserId(email) ?: throw Exception("User not found")
        return jobService.createJob(request, user)
    }

    @PutMapping("/update/{id}")
    fun updateJobById(@PathVariable id: Int, @RequestBody request: JobRequest): Job? {
        return jobService.updateJobById(id, request)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteJobById(@PathVariable id: Int): Boolean {
        return jobService.deleteJobById(id)
    }

    fun findUserId(email: String) : User? {
        return userRepository.findByEmail(email)?.orElse(null);
    }


    // Exception handler method
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFoundException(ex: Exception): ErrorResponse? {
        return ErrorResponse.builder(
            ex,
            HttpStatusCode.valueOf(500),
            ex.localizedMessage
        )
            .build()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JobPostingController::class.java)
    }

}