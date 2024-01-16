package com.kotlin.koomart.domain.member

import org.springframework.data.repository.Repository
import java.util.*

interface MemberRepository: Repository<Member, UUID> {
    fun findAll(): List<Member>
    fun findById(uuid: UUID): Member?
    fun save(member: Member): Member
    fun deleteById(uuid: UUID)

    // deleteAllInBatch를 사용하면 where절 없이 테이블에 대해 일괄삭제 쿼리를 날린다!
    fun deleteAllInBatch()
}