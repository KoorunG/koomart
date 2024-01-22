package com.kotlin.koomart.config

import com.kotlin.koomart.domain.common.FakerFactory
import com.kotlin.koomart.domain.member.MemberRepository
import com.kotlin.koomart.domain.post.PostRepository
import jakarta.persistence.EntityManager
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener

@Configuration
@Profile("default")
class InitConfig(
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository,
) {
    // 애플리케이션이 init되는 시점에 실행
    @EventListener(ApplicationReadyEvent::class)
    private fun init() {

        // faker로 10개의 초기 데이터 적재
        repeat(10) {
            val member = memberRepository.save(FakerFactory.fakeMember())
            println("member ::: [$it] ${member.loginId}")
        }
        memberRepository.findAll().forEach {
            val post = postRepository.save(FakerFactory.fakePost(it))
            println("post ::: [$it] ${post.title}")
        }
    }
}