package com.kotlin.koomart.service

import com.kotlin.koomart.api.request.MemberSaveRequest
import com.kotlin.koomart.api.response.MemberResponse
import com.kotlin.koomart.domain.member.Member
import com.kotlin.koomart.domain.member.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun findAll() = memberRepository.findAll().map {
        MemberResponse(
            loginId = it.loginId,
            name = it.name,
            password = it.password
        )
    }
    fun findMember(id: UUID): MemberResponse {
        val findMember = memberRepository.findById(id) ?: throw IllegalStateException("해당 멤버가 존재하지 않습니다! ::::: $id")
        return MemberResponse(
            loginId = findMember.loginId,
            name = findMember.name,
            password = findMember.password
        )
    }

    @Transactional
    fun save(request: MemberSaveRequest): UUID {
        val member = Member(
            loginId = request.loginId,
            name = request.name,
            password = request.password
        )
        memberRepository.save(member)
        return member.id
    }

    // DB에서 ID로 해당 회원을 조회한 뒤 삭제 로직을 돌려야함..
    @Transactional
    fun deleteMember(id: UUID): MemberResponse {
        val deleteMember = memberRepository.findById(id) ?: throw IllegalStateException("해당 멤버가 존재하지 않습니다! ::::: $id")
        memberRepository.deleteById(deleteMember.id)
        return MemberResponse(
            loginId = deleteMember.loginId,
            name = deleteMember.name,
            password = deleteMember.password
        )
    }

    @Transactional
    fun deleteAllMember() {
        memberRepository.deleteAllInBatch()
    }
}