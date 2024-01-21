package com.kotlin.koomart.domain.common

import com.kotlin.koomart.domain.member.Member
import com.kotlin.koomart.domain.post.Post
import io.github.serpro69.kfaker.faker

// Faker 라이브러리를 이용한 가상객체 생성 함수
object FakerFactory {
    fun fakeMember(): Member {
        val faker = faker { }
        return Member(
            loginId = faker.string.bothify("${faker.name.firstName().lowercase()}_####", false),
            name = faker.name.name(),
            password = faker.string.bothify("#?#?#?###???")
        )
    }

    fun fakePost(): Post {
        val faker = faker { fakerConfig { locale = "ko" } }
        // 랜덤 title 만들기
        val title = StringBuilder().let {
            repeat(7) { i ->
                it.append(faker.lorem.words() + " ")
                if(i == 6) it.append("입니다.")
            }
            it.toString()
        }

        // 랜덤 contents 만들기
        val contents = StringBuilder().let {
            repeat(20) { i ->
                it.append(faker.lorem.words() + " ")
                if (i % 10 == 0) it.append("\n")
            }
            it.toString()
        }
        return Post(
            title = title,
            contents = contents
        )
    }
}
