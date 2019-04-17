package io.github.alexbogovich.springintro

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@SpringBootApplication
@EnableMongoAuditing
class SpringIntroApplication

fun main(args: Array<String>) {
    runApplication<SpringIntroApplication>(*args)
}

data class SimpleEntity(
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    val id: String? = null,

    val name: String
) {
    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var created: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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


@RestController
@RequestMapping("/entity")
class SimpleEntityController(private val simpleEntityRepo: SimpleEntityRepo) {
    @GetMapping
    fun all() = simpleEntityRepo.findAll()
}
