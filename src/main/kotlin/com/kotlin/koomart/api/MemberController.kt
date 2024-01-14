package com.kotlin.koomart.api

import com.kotlin.koomart.api.request.MemberSaveRequest
import com.kotlin.koomart.api.response.MemberResponse
import com.kotlin.koomart.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/member")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping
    fun findAllMembers() = memberService.findAll().map { MemberResponse(
        loginId = it.loginId,
        name = it.name,
        password = it.password
    ) }

    @PostMapping
    fun saveMember(@RequestBody request: MemberSaveRequest): UUID {
        return memberService.save(request)
    }
}