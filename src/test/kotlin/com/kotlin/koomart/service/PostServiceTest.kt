package com.kotlin.koomart.service

import com.kotlin.koomart.domain.common.FakerFactory
import com.kotlin.koomart.domain.member.MemberRepository
import com.kotlin.koomart.domain.post.PostRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

// StringSpec을 사용하면 구문을 최소화 할 수 있다.
// BehaviorSpec을 사용하면 BDD 스타일로 테스트를 작성할 수 있다.

@SpringBootTest
@ActiveProfiles("test")
class PostServiceTest(
    postService: PostService,
    postRepository: PostRepository,
    memberRepository: MemberRepository
) : BehaviorSpec({

    beforeSpec {
        postRepository.deleteAllInBatch()
        memberRepository.deleteAllInBatch()
    }

    afterSpec {
        postRepository.deleteAllInBatch()
        memberRepository.deleteAllInBatch()
    }

    Given("DB에 글을 10개 적재한다.") {
        repeat(10) {
            val member = memberRepository.save(FakerFactory.fakeMember())
            postRepository.save(FakerFactory.fakePost(member))
        }
        When("글을 모두 조회한다.") {
            val posts = postService.findAllPosts()
            Then("글이 10개임을 검증한다.") {
                posts.size shouldBe 10
            }
        }
    }
})