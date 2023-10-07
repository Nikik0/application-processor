package com.nikik0.applicationprocessor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApplicationProcessorApplication

fun main(args: Array<String>) {
	runApplication<ApplicationProcessorApplication>(*args)
}
