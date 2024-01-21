package com.kotlin.koomart.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

// StringSpec을 사용하면 구문을 최소화 할 수 있다.
// BehaviorSpec을 사용하면 BDD 스타일로 테스트를 작성할 수 있다.

@SpringBootTest
@ActiveProfiles("test")
class PostServiceTest(postService: PostService) : BehaviorSpec({

    Given("DB에 글이 6개 존재한다.") {
        val posts = postService.findAll()
        Then("이 글이 6개임을 검증한다.") {
            posts.size shouldBe 6
        }
    }
})