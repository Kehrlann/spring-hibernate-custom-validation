package wf.garnier.spring.hibernatefun

import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.*

@Entity
data class Device(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,

        @ManyToOne
        val user: User = User(),
        val name: String = "",
        val creationDate: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC)
)

interface DeviceRepository: CrudRepository<Device, Int>