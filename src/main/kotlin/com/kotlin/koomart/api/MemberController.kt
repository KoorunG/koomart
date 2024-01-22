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
    fun findAllMembers() = memberService.findAll()

    @GetMapping("/{id}")
    fun findMember(@PathVariable id: UUID): MemberResponse = memberService.findMember(id)

    @PostMapping
    fun saveMember(@RequestBody request: MemberSaveRequest): UUID {
        return memberService.save(request)
    }

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: UUID) = memberService.deleteMember(id)
}