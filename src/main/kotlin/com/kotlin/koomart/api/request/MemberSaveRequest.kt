package com.kotlin.koomart.api.request

// 멤버를 저장할 때 사용하는 DTO
data class MemberSaveRequest(
    val loginId: String,
    val name: String,
    val password: String
)