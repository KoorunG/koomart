package com.kotlin.koomart.api

import com.kotlin.koomart.api.request.PostSaveRequest
import com.kotlin.koomart.api.response.PostResponse
import com.kotlin.koomart.domain.post.Post
import com.kotlin.koomart.service.PostService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postService: PostService,
) {
    // 엔티티 <-> DTO 변환은 컨트롤러에서 하도록 한다. (일단은...)
    @GetMapping
    fun findAllMembers() = postService.findAll().map {
        PostResponse(
            title = it.title,
            contents = it.contents
        )
    }

    @GetMapping("/{id}")
    fun findPost(@PathVariable id: UUID): PostResponse {
        val findPost = postService.findPost(id)
        return PostResponse(
            title = findPost.title,
            contents = findPost.contents
        )
    }

    @PostMapping
    fun savePost(@RequestBody request: PostSaveRequest): UUID {
        return postService.save(
            Post(
                title = request.title,
                contents = request.contents
            )
        )
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: UUID): PostResponse {
        val deletePost = postService.deletePost(id)
        return PostResponse(
            title = deletePost.title,
            contents = deletePost.contents
        )
    }
}