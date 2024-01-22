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
    @GetMapping
    fun findAllPosts() = postService.findAllPosts()

    @GetMapping("/{id}")
    fun findPost(@PathVariable id: UUID): PostResponse = postService.findPost(id)

    @PostMapping
    fun savePost(@RequestBody request: PostSaveRequest): UUID {
        return postService.save(request)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: UUID): PostResponse = postService.deletePost(id)
}