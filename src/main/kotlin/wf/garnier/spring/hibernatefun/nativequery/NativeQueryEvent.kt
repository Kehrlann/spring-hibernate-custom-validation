package wf.garnier.spring.hibernatefun.nativequery

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.*

@Entity
@Table(name = "event")
data class NativeQueryEvent(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,
        val value: Int = Int.MIN_VALUE,
        val creationDate: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC)
)

interface EventRepository : CrudRepository<NativeQueryEvent, Int> {
    @Query("""
        SELECT DISTINCT ON(event.device_id) event.*
            FROM event
            JOIN device ON event.device_id = device.id
            WHERE device.user_id = ?
        ORDER BY event.device_id, event.creation_date DESC;
    """, nativeQuery = true)
    fun latestEventsByUserId_DistinctOn(userId: Int): Collection<NativeQueryEvent>

    @Query("""
        SELECT event.*
          FROM event
          JOIN
             (SELECT event.device_id, max(event.creation_date) as creation_date
                FROM event
                JOIN device ON event.device_id = device.id
              WHERE device.user_id = 1
              GROUP BY device_id) as filtered_event
          ON
                event.creation_date = filtered_event.creation_date
            AND event.device_id = filtered_event.device_id;
    """, nativeQuery = true)
    fun latestEventsByUserId_InnerJoin(userId: Int): Collection<NativeQueryEvent>
}