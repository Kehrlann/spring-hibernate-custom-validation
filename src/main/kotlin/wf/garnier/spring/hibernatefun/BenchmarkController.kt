package wf.garnier.spring.hibernatefun

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.system.measureTimeMillis

@RestController
class BenchmarkController(
        val userRepository: UserRepository,
        val eventRepository: EventRepository,
        val eventLoader: EventLoader
) {
    @GetMapping("/benchmark")
    fun benchMark(): String {
        val user = userRepository.findAll().first()
        val hibernateLoadTime =
                measureTimeMillis {
                    val events = eventLoader.latestEventsByUserId(user.id)
                }

        val nativeQueryLoadTime =
                measureTimeMillis {
                    val events = eventRepository.latestEventsByUserId(user.id)
                }

        return "Benchmark results : Native query -> ${nativeQueryLoadTime}ms, Hibernate -> ${hibernateLoadTime}ms"
    }
}