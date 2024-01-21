package com.kotlin.koomart.api.response

import com.kotlin.koomart.domain.member.Member

data class PostResponse(
    val title: String,
    val contents: String,
    val author: String,
)