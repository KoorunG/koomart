package com.kotlin.koomart.config

import com.kotlin.koomart.domain.member.Member
import com.kotlin.koomart.domain.member.MemberRepository
import io.github.serpro69.kfaker.faker
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@Configuration
class InitConfig(
    private val memberRepository: MemberRepository,
) {
    val faker = faker { fakerConfig { locale = "en-US" } }

    // 애플리케이션이 init되는 시점에 실행
    @EventListener(ApplicationReadyEvent::class)
    private fun init() {
        // fixture로 CUD 테스트용 데이터 삽입
        // key값이 "00000000-0000-0000-0000-000000000000"
        memberRepository.save(Member.fixture())

        // faker로 mock 데이터 삽입
        repeat(5) {
            val name = faker.name.let { n -> "${n.firstName()} ${n.lastName()}" }
            val saved = memberRepository.save(
                Member(
                    loginId = faker.string.bothify("${name.split(" ")[0].lowercase()}_####", false),
                    name = name,
                    password = faker.string.bothify("#?#?#?###???")
                )
            )
            println("member saved ::: [$it] ${saved.id}")
        }
    }
}