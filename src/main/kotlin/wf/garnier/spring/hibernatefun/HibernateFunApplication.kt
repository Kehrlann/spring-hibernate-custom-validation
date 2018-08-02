package wf.garnier.spring.hibernatefun

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HibernateFunApplication: CommandLineRunner {
    override fun run(vararg args: String?) {
        val logger = LoggerFactory.getLogger(HibernateFunApplication::class.java)
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        logger.info("App started ! Visit /benchmark to start using it.")
    }
}

fun main(args: Array<String>) {
    runApplication<HibernateFunApplication>(*args)
}
