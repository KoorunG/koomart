package com.kotlin.koomart.domain.post

import com.kotlin.koomart.domain.common.PrimaryKeyEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.util.*

@Entity
class Post(
    title: String,
    contents: String,
    _id: UUID? = null
): PrimaryKeyEntity(_id) {

    @Column(nullable = false)
    var title = title
        protected set

    @Column(nullable = true)
    var contents = contents
        protected set

    companion object {
        fun fixture(
            id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
            title: String = "테스트글 제목",
            contents: String = "테스트 글 내용"
        ): Post {
            return Post(_id = id, title = title, contents = contents)
        }
    }
}