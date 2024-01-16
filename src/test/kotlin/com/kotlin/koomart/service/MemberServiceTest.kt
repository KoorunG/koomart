package com.kotlin.koomart.service

import com.kotlin.koomart.domain.member.MemberFactory
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService
) {
    @BeforeEach
    fun init() {
        memberService.deleteAllMember()
        repeat(5) { memberService.save(MemberFactory.fakeMember()) }
    }

    @AfterEach
    fun cleanUp(){
        memberService.deleteAllMember()
    }

    @Test
    fun findAll() {
        val members = memberService.findAll()
        assertThat(members.size).isEqualTo(5)
    }

    @Test
    fun `멤버 저장 테스트`() {
        val fakeMember = MemberFactory.fakeMember()
        memberService.save(fakeMember)

        val members = memberService.findAll()
        assertThat(members.size).isEqualTo(6)
    }

    @Test
    fun `멤버 삭제 테스트`() {
        val before = memberService.findAll()
        val random = before.random()
        memberService.deleteMember(random.id)
        val after = memberService.findAll()
        assertThat(after.size).isEqualTo(4)
        println("변경내용 확인")
    }

}