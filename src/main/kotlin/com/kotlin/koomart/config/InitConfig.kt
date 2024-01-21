package com.kotlin.koomart.config

import com.kotlin.koomart.domain.common.FakerFactory
import com.kotlin.koomart.domain.member.Member
import com.kotlin.koomart.domain.member.MemberRepository
import com.kotlin.koomart.domain.post.Post
import com.kotlin.koomart.domain.post.PostRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@Configuration
class InitConfig(
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository,
) {
    // 애플리케이션이 init되는 시점에 실행
    @EventListener(ApplicationReadyEvent::class)
    private fun init() {
        // fixture로 CUD 테스트용 데이터 삽입
        // key값이 "00000000-0000-0000-0000-000000000000"
        memberRepository.save(Member.fixture())
        postRepository.save(Post.fixture())

        // faker로 mock 데이터 삽입
        repeat(5) {
            val member = memberRepository.save(FakerFactory.fakeMember())
            println("member ::: [$it] ${member.loginId}")

            val post = postRepository.save(FakerFactory.fakePost())
            println("post ::: [$it] ${post.title}")
        }
    }
}