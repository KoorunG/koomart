package com.kotlin.koomart.service

import com.kotlin.koomart.api.request.PostSaveRequest
import com.kotlin.koomart.domain.member.MemberRepository
import com.kotlin.koomart.domain.post.Post
import com.kotlin.koomart.domain.post.PostRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService(
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository
) {
    fun findAll() = postRepository.findAll()
    fun findPost(id: UUID) = postRepository.findById(id) ?: throw IllegalStateException("해당 글이 존재하지 않습니다! ::::: $id")

    @Transactional
    fun save(request: PostSaveRequest): UUID {
        val author = memberRepository.findById(request.authorId) ?: throw IllegalStateException("해당 작성자가 존재하지 않습니다! ::::: ${request.authorId}")
        val saved = postRepository.save(Post(
            title = request.title,
            contents = request.contents,
            author = author
        ))
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