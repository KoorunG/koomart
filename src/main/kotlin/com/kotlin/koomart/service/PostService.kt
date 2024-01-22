package com.kotlin.koomart.service

import com.kotlin.koomart.api.request.PostSaveRequest
import com.kotlin.koomart.api.response.PostResponse
import com.kotlin.koomart.domain.member.MemberRepository
import com.kotlin.koomart.domain.post.Post
import com.kotlin.koomart.domain.post.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class PostService(
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository
) {
    fun findAllPosts() = postRepository.findAll().map {
        PostResponse(
            title = it.title,
            contents = it.contents,
            author = it.author.name
        )
    }
    fun findPost(id: UUID): PostResponse {
        val findPost = postRepository.findById(id) ?: throw IllegalStateException("해당 글이 존재하지 않습니다! ::::: $id")
        return PostResponse(
            title = findPost.title,
            contents = findPost.contents,
            author = findPost.author.name
        )
    }

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
    fun deletePost(id: UUID): PostResponse {
        val deletePost = postRepository.findById(id) ?: throw IllegalStateException("해당 글이 존재하지 않습니다! ::::: $id")
        postRepository.deleteById(deletePost.id)
        return PostResponse(
            title = deletePost.title,
            contents = deletePost.contents,
            author = deletePost.author.name
        )
    }
    @Transactional
    fun deleteAllPost() {
        postRepository.deleteAllInBatch()
    }
}