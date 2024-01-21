package com.kotlin.koomart.api.request

import com.kotlin.koomart.domain.member.Member
import java.util.UUID

// 멤버를 저장할 때 사용하는 DTO
data class PostSaveRequest(
    val title: String,
    val contents: String,
    val authorId: UUID,
)