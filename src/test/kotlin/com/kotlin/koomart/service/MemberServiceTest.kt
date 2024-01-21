package com.kotlin.koomart.service

import com.kotlin.koomart.domain.common.FakerFactory
import com.kotlin.koomart.domain.member.MemberRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import jakarta.transaction.Transactional
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
) : BehaviorSpec({
    // 테스트 전에 DB에 미리 저장된 데이터 초기화
    beforeSpec {
        memberRepository.deleteAllInBatch()
    }

    Given("DB에 미리 저장된 멤버가 존재한다.") {
        repeat(5) {
            memberRepository.save(FakerFactory.fakeMember())
        }
        When("모든 멤버를 조회한다.") {
            val members = memberService.findAll()
            Then("멤버가 5명임을 검증한다.") {
                members.size shouldBe 5
            }
        }

        When("멤버 하나를 저장한다.") {
            memberService.save(FakerFactory.fakeMember())
            val members = memberService.findAll()
            Then("멤버가 6명임을 검증한다.") {
                members.size shouldBe 6
            }
        }

        When("모든 멤버를 삭제한다.") {
            memberService.deleteAllMember()
            val members = memberService.findAll()
            Then("멤버가 0명임을 검증한다.") {
                members.size shouldBe 0
            }
        }
    }
})