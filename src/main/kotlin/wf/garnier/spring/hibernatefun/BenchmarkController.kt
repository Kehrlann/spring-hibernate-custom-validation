package wf.garnier.spring.hibernatefun

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import wf.garnier.spring.hibernatefun.nativequery.EventRepository
import wf.garnier.spring.hibernatefun.purehibernate.EventLoader
import wf.garnier.spring.hibernatefun.purehibernate.UserRepository
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

        val distinctOnTime =
                measureTimeMillis {
                    val events = eventRepository.latestEventsByUserId_DistinctOn(user.id)
                }

        val innerJoinTime =
                measureTimeMillis {
                    val events = eventRepository.latestEventsByUserId_InnerJoin(user.id)
                }

        return "Benchmark results :\n" +
                "- Native query, DISTINCT ON -> ${distinctOnTime}ms\n" +
                "- Native query, INNER JOIN -> ${innerJoinTime}ms\n" +
                "- Hibernate -> ${hibernateLoadTime}ms"
    }
}