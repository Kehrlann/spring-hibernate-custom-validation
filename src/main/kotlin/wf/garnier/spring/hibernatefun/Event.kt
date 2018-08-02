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

interface EventRepository: CrudRepository<Event, Int> {
    @Query("""
        SELECT DISTINCT ON(event.device_id) event.*
            FROM event
            JOIN device ON event.device_id = device.id
            WHERE device.user_id = ?
        ORDER BY event.device_id, event.creation_date DESC;
    """, nativeQuery = true)
    fun latestEventsByUserId(userId: Int): Collection<Event>
}

@Service
class EventLoader(val userRepository: UserRepository) {
    @Transactional
    fun latestEventsByUserId(userId: Int): Collection<Event> =
            userRepository.findById(userId).get()   // get user
                    .devices                        // get devices for that users
                    .map { it.lastEvent }           // get latest events for that device
                    .filterNotNull()                // remove nulls in case a device has no event
}