package com.uncc.jobboard.auth

import com.uncc.jobboard.user.Role

data class AuthenticationResponse (
    val firstname: String,
    val lastname: String,
    val email: String,
    val role: Role?,
    val token: String
)