package com.kotlin.koomart.domain.member

import com.kotlin.koomart.domain.common.PrimaryKeyEntity
import io.github.serpro69.kfaker.faker
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.util.UUID

@Entity
class Member(
    loginId: String,
    name: String,
    password: String,
    _id: UUID? = null
) : PrimaryKeyEntity(_id) {

    @Column(nullable = false)
    var loginId = loginId
        protected set

    @Column(nullable = false)
    var name = name
        protected set

    @Column(nullable = false)
    var password = password
        protected set
}

// 테스트용 멤버를 생성하는 Factory
object MemberFactory {
    private val faker = faker {  }
    fun fixture(
        id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
        loginId: String = "testid",
        password: String = "testpw",
        name: String = "테스트용"
    ): Member {
        return Member(_id = id, loginId = loginId, password = password, name = name)
    }
    fun fakeMember() = Member(
        loginId = faker.string.bothify("${faker.name.firstName().lowercase()}_####", false),
        name = faker.name.name(),
        password = faker.string.bothify("#?#?#?###???")
    )
}