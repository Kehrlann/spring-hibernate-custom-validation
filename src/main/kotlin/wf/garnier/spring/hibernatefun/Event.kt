package wf.garnier.spring.hibernatefun

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.*

@Entity
class Event(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,

        @ManyToOne(fetch = FetchType.LAZY)
        val device: Device = Device(),
        val value: Int = Int.MIN_VALUE,
        val creationDate: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC)
) {
    override fun toString() = "Event(id=$id, value=$value, creationDate=$creationDate)"
}

interface EventRepository: CrudRepository<Event, Int>