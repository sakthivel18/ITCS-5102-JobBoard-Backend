package com.uncc.jobboard.jobPosting

import com.uncc.jobboard.job.Job
import com.uncc.jobboard.job.JobRepository
import com.uncc.jobboard.user.User
import org.springframework.stereotype.Service
import java.time.LocalDate
import com.uncc.jobboard.job.Job as JobEntity

@Service
class JobPostingService(
        private val jobRepo : JobRepository

) {
    fun getAllJobs(): MutableList<JobEntity?> = jobRepo.findAll()

    fun getJobById(id: Int): JobEntity? = jobRepo.findById(id).orElse(null)

    fun createJob(request: JobRequest, user: User): JobEntity {

        val job = JobEntity(

                title = request.title,
                companyName = request.companyName,
                description = request.description,
                location = request.location,
                experience = request.experience,
                jobType = request.jobType,
                workMode = request.workMode,
                addedDate = LocalDate.now(),
                applicantsApplied = request.applicantsApplied,
                createdBy = user.email,
                applicationLink = request.applicationLink,
                user = user
        )
        return jobRepo.save(job)
    }

    fun updateJobById(id: Int,request: JobRequest):JobEntity? {
        val job = jobRepo.findById(id).orElse(null)
        if(job != null){
            job.title = request.title
            job.companyName = request.companyName
            job.description = request.description
            job.location = request.location
            job.experience = request.experience
            job.jobType = request.jobType
            job.workMode = request.workMode
            job.applicantsApplied = request.applicantsApplied
            job.createdBy = request.createdBy
            job.applicationLink = request.applicationLink

            return jobRepo.save(job)

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

    fun getAllJobsOfUser(user: User): MutableList<Job?>? {
        return jobRepo.findAll()
            .stream()
            .filter { j -> j?.createdBy == user.email }
            .toList()
    }

}