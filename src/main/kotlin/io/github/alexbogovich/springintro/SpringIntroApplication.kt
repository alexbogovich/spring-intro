package io.github.alexbogovich.springintro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class SpringIntroApplication

fun main(args: Array<String>) {
	runApplication<SpringIntroApplication>(*args)
}

@RestController
//@Controller + @ResponseBody
class SimpleController {

	@GetMapping("/hello")
	fun helloAll() = "hello all!"
	@GetMapping("/hello/{name}")
	fun helloAll(@PathVariable name: String) = "hello $name!"

	@GetMapping("/query")
	fun query(@RequestParam("param") name: Int) = "param = $name!"

	@PostMapping("/post")
	fun post(@RequestBody body: SimpleRequest) = "body = $body!"

}

data class SimpleRequest(val name: String)
