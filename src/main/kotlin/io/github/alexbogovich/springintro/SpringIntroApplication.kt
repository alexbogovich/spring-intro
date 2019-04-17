package io.github.alexbogovich.springintro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringIntroApplication

fun main(args: Array<String>) {
	runApplication<SpringIntroApplication>(*args)
}
