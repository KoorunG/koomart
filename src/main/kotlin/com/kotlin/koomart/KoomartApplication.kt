package com.kotlin.koomart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KoomartApplication

fun main(args: Array<String>) {
	runApplication<KoomartApplication>(*args)
}
