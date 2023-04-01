package com.uncc.jobboard.jobPosting

import com.uncc.jobboard.job.Job
import com.uncc.jobboard.job.JobRepository
import com.uncc.jobboard.user.User
import com.uncc.jobboard.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import com.uncc.jobboard.job.Job as Job1

@Service
class JobPostingService(
        private val jobRepo : JobRepository,
        private val userRepo: UserRepository,

) {
    fun getAllJobs(): MutableList<Job1?> = jobRepo.findAll()

    fun getJobById(id: Int): Job1? = jobRepo.findById(id).orElse(null)

    fun createJob(request: JobRequest): Job1 {
        val user = userRepo.findById(request.userId).orElse(null)

        val job = Job1(

                title = request.title,
                companyName = request.companyName,
                description = request.description,
                location = request.location,
                experience = request.experience,
                jobType = request.jobType,
                workMode = request.workMode,
                addedDate = LocalDate.now(),
                applicantsApplied = request.applicantsApplied,
                isCreated = true,
                applicationLink = request.applicationLink,
                user = user
        )
        return jobRepo.save(job)
    }

    fun updateJobById(id: Int,request: JobRequest):Job1? {
        val job = jobRepo.findById(id).orElse(null)
        if(job!=null){
            job.title = request.title
            job.companyName = request.companyName
            job.description = request.description
            job.location = request.location
            job.experience = request.experience
            job.jobType = request.jobType
            job.workMode = request.workMode
            job.addedDate = request.addedDate
            job.applicantsApplied = request.applicantsApplied
            job.isCreated = request.isCreated
            job.applicationLink = request.applicationLink

            return  jobRepo.save(job)

        }
        return null
    }



    fun deleteJobById(id: Int):Boolean{
        val job = jobRepo.findById(id).orElse(null)
        if(job!=null){
            jobRepo.delete(job)
            return true
        }
        return false
    }
}