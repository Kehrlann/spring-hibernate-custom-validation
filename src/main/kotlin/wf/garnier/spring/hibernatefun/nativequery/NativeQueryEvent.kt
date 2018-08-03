package wf.garnier.spring.hibernatefun.nativequery

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
