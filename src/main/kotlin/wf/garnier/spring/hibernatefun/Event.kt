package wf.garnier.spring.hibernatefun

import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.*

@Entity
data class Event(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,

        @ManyToOne(fetch = FetchType.LAZY)
        val device: Device = Device(),
        val value: Int = Int.MIN_VALUE,
        val creationDate: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC)
)

interface EventRepository: CrudRepository<Event, Int>