package wf.garnier.spring.hibernatefun.purehibernate

import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.*

@Entity
@Table(name = "application_user")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,
        val email: String = "",
        val creationDate: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC),

        @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
        val devices: List<Device> = listOf()
) {
    override fun toString() = "User(id=$id,email=$email)"
}

interface UserRepository : CrudRepository<User, Int>