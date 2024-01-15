package com.kotlin.koomart.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService
) {
    @Test
    fun findAll() {
        val members = memberService.findAll()
        assertThat(members.size).isEqualTo(5)
    }
}