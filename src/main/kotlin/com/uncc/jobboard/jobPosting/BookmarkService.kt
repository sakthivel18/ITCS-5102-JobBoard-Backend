package com.uncc.jobboard.jobPosting

import com.uncc.jobboard.job.Job
import com.uncc.jobboard.job.JobRepository
import org.springframework.stereotype.Service

@Service
class BookmarkService(
    private val jobRepo : JobRepository
) {

    fun getBookmarks(email: String): MutableList<Job?> {
        return jobRepo.findAll()
            .stream()
            .filter { j -> j?.bookmarkedBy?.contains(email) == true }
            .toList()
    }

    fun addBookmark(email : String, request: JobRequest, id: Int): Job? {
        val job = jobRepo.findById(id).orElse(null)
        if (job != null) {
            var bookmarkedBy = ArrayList<String>(request.bookmarkedBy)
            if (!bookmarkedBy.contains(email)) {
                bookmarkedBy.add(email)
            }
            job.bookmarkedBy = bookmarkedBy
            return jobRepo.save(job)
        }
        return null
    }


    fun removeBookmark(email : String, request: JobRequest, id: Int): Job? {
        val job = jobRepo.findById(id).orElse(null)
        if (job != null) {
            var bookmarkedBy = ArrayList<String>(request.bookmarkedBy)
            bookmarkedBy.remove(email)
            job.bookmarkedBy = bookmarkedBy
            return jobRepo.save(job)
        }
        return null
    }



}