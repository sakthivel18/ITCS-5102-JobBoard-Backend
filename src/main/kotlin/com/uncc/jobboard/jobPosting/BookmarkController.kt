package com.uncc.jobboard.jobPosting

import com.uncc.jobboard.config.JwtService
import com.uncc.jobboard.job.Job
import org.springframework.web.bind.annotation.*


//@CrossOrigin(
//    origins = ["http://localhost:3000"],
//    maxAge = 3600L,
//    allowCredentials = "true",
//    allowedHeaders = ["Authorization", "Cache-Control", "Content-Type"],
//    exposedHeaders = ["X-Get-Header"]
//)
@RestController
@RequestMapping("/api/v1/bookmarks")
class BookmarkController(
    private val jwtService: JwtService,
    private val bookmarkService: BookmarkService
) {

    @GetMapping
    fun getBookmarks(@RequestHeader("Authorization") token: String): MutableList<Job?> {
        val email = jwtService.extractUsername(token.substring(7))
        return bookmarkService.getBookmarks(email)
    }

    @PostMapping("/add/{id}")
    fun addBookmark(@RequestHeader("Authorization") token: String, @RequestBody request: JobRequest, @PathVariable id: Int): Job? {
        val email = jwtService.extractUsername(token.substring(7))
        return bookmarkService.addBookmark(email, request, id)
    }

    @PostMapping("/remove/{id}")
    fun removeBookmark(@RequestHeader("Authorization") token: String, @RequestBody request: JobRequest, @PathVariable id: Int): Job? {
        val email = jwtService.extractUsername(token.substring(7))
        return bookmarkService.removeBookmark(email, request, id)
    }

}