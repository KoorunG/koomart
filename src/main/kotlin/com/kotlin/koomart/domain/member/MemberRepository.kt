package com.kotlin.koomart.domain.member

import org.springframework.data.repository.Repository
import java.util.*

interface MemberRepository: Repository<Member, UUID> {
    fun findAll(): List<Member>
    fun findById(uuid: UUID): Member
    fun save(member: Member): Member
}