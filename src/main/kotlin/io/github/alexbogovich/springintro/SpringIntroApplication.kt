package io.github.alexbogovich.springintro

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@SpringBootApplication
@EnableMongoAuditing
class SpringIntroApplication

fun main(args: Array<String>) {
    runApplication<SpringIntroApplication>(*args)
}


data class SimpleEntity(
    @Id
    val id: String? = null,
    val name: String
) {
    @CreatedDate
    var created: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    var updated: LocalDateTime = LocalDateTime.now()
}

interface SimpleEntityRepo: MongoRepository<SimpleEntity, String>

@Component
class Bootstrap(private val simpleEntityRepo: SimpleEntityRepo): CommandLineRunner {
    override fun run(vararg args: String?) {
        if (simpleEntityRepo.count() == 0L) {
            simpleEntityRepo.saveAll(listOf(
                SimpleEntity(name = "alpha"),
                SimpleEntity(name = "beta"),
                SimpleEntity(name = "gamma")
            ))
        }
    }
}
