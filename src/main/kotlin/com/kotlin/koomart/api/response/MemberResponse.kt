package com.kotlin.koomart.api.response

data class MemberResponse(
    val loginId: String,
    val name: String,
    val password: String
)