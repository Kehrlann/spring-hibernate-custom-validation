package wf.garnier.spring.hibernatefun

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HibernateFunApplication(eventRepo: EventRepository) {

    init {
        val tenEvents = eventRepo.findAll().take(10)
        println(tenEvents)
    }
}

fun main(args: Array<String>) {
    runApplication<HibernateFunApplication>(*args)
}
