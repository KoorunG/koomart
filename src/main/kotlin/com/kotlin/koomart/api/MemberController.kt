package com.kotlin.koomart.api

import com.kotlin.koomart.api.request.MemberSaveRequest
import com.kotlin.koomart.api.response.MemberResponse
import com.kotlin.koomart.service.MemberService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping
    fun findAllMembers() = memberService.findAll().map {
        MemberResponse(
            loginId = it.loginId,
            name = it.name,
            password = it.password
        )
    }

    @GetMapping("/{id}")
    fun findMember(@PathVariable id: UUID): MemberResponse {
        val findMember = memberService.findMember(id)
        return MemberResponse(
            loginId = findMember.loginId,
            name = findMember.name,
            password = findMember.password
        )
    }

    @PostMapping
    fun saveMember(@RequestBody request: MemberSaveRequest): UUID {
        return memberService.save(request)
    }

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: UUID): MemberResponse {
        val deleteMember = memberService.deleteMember(id)
        return MemberResponse(
            loginId = deleteMember.loginId,
            name = deleteMember.name,
            password = deleteMember.password
        )
    }
}