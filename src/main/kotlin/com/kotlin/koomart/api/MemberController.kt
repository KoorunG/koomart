package com.kotlin.koomart.api

import com.kotlin.koomart.api.request.MemberSaveRequest
import com.kotlin.koomart.api.response.MemberResponse
import com.kotlin.koomart.domain.member.Member
import com.kotlin.koomart.service.MemberService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService
) {
    // 엔티티 <-> DTO 변환은 컨트롤러에서 하도록 한다. (일단은...)
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
        return memberService.save(
            Member(
                loginId = request.loginId,
                name = request.name,
                password = request.password
            )
        )
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