package wf.garnier.spring.hibernatefun.purehibernate

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

@Service
class EventLoader(val userRepository: UserRepository) {
    @Transactional
    fun latestEventsByUserId(userId: Int): Collection<Event> =
            userRepository.findById(userId).get()   // get user
                    .devices                        // get devices for that users
                    .map { it.lastEvent }           // get latest events for that device
                    .filterNotNull()                // remove nulls in case a device has no event
}