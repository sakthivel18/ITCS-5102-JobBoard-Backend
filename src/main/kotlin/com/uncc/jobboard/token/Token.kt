package com.uncc.jobboard.token

import com.fasterxml.jackson.annotation.JsonIgnore
import com.uncc.jobboard.user.User
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
data class Token (
        @Id
        @GeneratedValue
        var id: Int ?= null,

        @Column(unique = true)
        var token: String,

        @Enumerated(EnumType.STRING)
        var tokenType: TokenType = TokenType.BEARER,
        var revoked: Boolean = false,
        var expired: Boolean = false,

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "user_id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        var user: User
)
