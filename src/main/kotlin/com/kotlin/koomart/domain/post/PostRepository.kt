package com.kotlin.koomart.domain.post

import org.springframework.data.repository.Repository
import java.util.*

interface PostRepository: Repository<Post, UUID> {
    fun findAll(): List<Post>
    fun findById(uuid: UUID): Post?
    fun save(post: Post): Post
    fun deleteById(uuid: UUID)

    // deleteAllInBatch를 사용하면 where절 없이 테이블에 대해 일괄삭제 쿼리를 날린다!
    fun deleteAllInBatch()
}