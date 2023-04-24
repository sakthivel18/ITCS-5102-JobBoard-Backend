package com.uncc.jobboard.auth

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.*


//@CrossOrigin(
//    origins = ["http://localhost:3000"],
//    maxAge = 3600L,
//    allowCredentials = "true",
//    allowedHeaders = ["Authorization", "Cache-Control", "Content-Type"],
//    exposedHeaders = ["X-Get-Header"]
//)
@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(private val service: AuthenticationService) {

    @PostMapping("/register")
    fun register(
            @RequestBody request: RegisterRequest?
    ): ResponseEntity<AuthenticationResponse> {
        val response : AuthenticationResponse = service!!.register(request!!)
        return ResponseEntity.ok<AuthenticationResponse>(response)
    }

    @PostMapping("/authenticate")
    fun authenticate(
            @RequestBody request: AuthenticationRequest?
    ): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok<AuthenticationResponse>(service!!.authenticate(request!!))
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
        private val logger = LoggerFactory.getLogger(AuthenticationController::class.java)
    }
}