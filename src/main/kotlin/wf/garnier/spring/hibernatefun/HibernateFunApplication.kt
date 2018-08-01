package wf.garnier.spring.hibernatefun

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.measureTimeMillis

@SpringBootApplication
class HibernateFunApplication(eventRepo: EventRepository) {

    init {
        val initTime = measureTimeMillis {
            val tenEvents = eventRepo.findAll().take(10)
            tenEvents.forEach(System.out::println)
        }
        println("Load finished, in ${initTime}ms !")
    }
}

fun main(args: Array<String>) {
    runApplication<HibernateFunApplication>(*args)
}
