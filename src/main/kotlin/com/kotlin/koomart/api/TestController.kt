package com.kotlin.koomart.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class TestController {

    @GetMapping("/{value}")
    fun hello(@PathVariable value: String) = "Hello, $value"
}