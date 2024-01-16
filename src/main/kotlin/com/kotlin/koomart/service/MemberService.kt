package com.kotlin.koomart.service

import com.kotlin.koomart.domain.member.Member
import com.kotlin.koomart.domain.member.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun findAll() = memberRepository.findAll()
    fun findMember(id: UUID) = memberRepository.findById(id) ?: throw IllegalStateException("해당 멤버가 존재하지 않습니다! ::::: $id")

    @Transactional
    fun save(member: Member): UUID {
        val saved = memberRepository.save(member)
        return saved.id
    }

    // DB에서 ID로 해당 회원을 조회한 뒤 삭제 로직을 돌려야함..
    @Transactional
    fun deleteMember(id: UUID): Member {
        val findMember = findMember(id)
        memberRepository.deleteById(findMember.id)
        return findMember
    }
    @Transactional
    fun deleteAllMember() {
        memberRepository.deleteAllInBatch()
    }
}