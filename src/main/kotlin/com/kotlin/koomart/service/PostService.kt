package com.kotlin.koomart.service

import com.kotlin.koomart.domain.post.Post
import com.kotlin.koomart.domain.post.PostRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun findAll() = postRepository.findAll()
    fun findPost(id: UUID) = postRepository.findById(id) ?: throw IllegalStateException("해당 글이 존재하지 않습니다! ::::: $id")

    @Transactional
    fun save(post: Post): UUID {
        val saved = postRepository.save(post)
        return saved.id
    }

    // DB에서 ID로 해당 회원을 조회한 뒤 삭제 로직을 돌려야함..
    @Transactional
    fun deletePost(id: UUID): Post {
        val findPost = findPost(id)
        postRepository.deleteById(findPost.id)
        return findPost
    }
    @Transactional
    fun deleteAllPost() {
        postRepository.deleteAllInBatch()
    }
}