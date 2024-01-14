package com.kotlin.koomart.service

import com.kotlin.koomart.api.request.MemberSaveRequest
import com.kotlin.koomart.domain.member.Member
import com.kotlin.koomart.domain.member.MemberRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberService(
    private val memberRepository: MemberRepository
){
    fun findAll()= memberRepository.findAll()
    fun save(command: MemberSaveRequest): UUID {
        val saved = memberRepository.save(
            Member(
                loginId = command.loginId,
                name = command.name,
                password = command.password
            )
        )
        return saved.id
    }
}