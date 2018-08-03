package wf.garnier.spring.hibernatefun.purehibernate

import org.hibernate.annotations.JoinFormula
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.*

@Entity
class Device(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,

        val name: String = "",
        val creationDate: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC),

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="user_id", nullable=false)
        val user: User = User(),

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinFormula("(SELECT event.id FROM event WHERE event.device_id = id ORDER BY event.creation_date LIMIT 1)")
        val lastEvent: Event? = null
) {
    override fun toString() = "Device(id=$id,name=$name)"
}

interface DeviceRepository: CrudRepository<Device, Int>