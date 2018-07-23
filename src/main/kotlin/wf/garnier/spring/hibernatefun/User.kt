package wf.garnier.spring.hibernatefun

import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.*

@Entity
@Table(name = "application_user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,
        val email: String = "",
        val creationDate: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC)
)

interface UserRepository: CrudRepository<User, Int>