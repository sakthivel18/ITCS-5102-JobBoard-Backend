package com.uncc.jobboard.auth

import com.uncc.jobboard.user.Role

data class RegisterRequest (
        val firstname: String,
        val lastname: String,
        val email: String,
        val password: String,
        val role: String
)