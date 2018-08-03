package wf.garnier.spring.hibernatefun.schemavalidation

import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.*

// This does not pass Hibernate validation :
// there is an "fake" field that is not in the DB, and it is not marked @Transient
// org.hibernate.tool.schema.spi.SchemaManagementException: Schema-validation: missing column [fake] in table [application_user]
// But we skip validating that Table through a custom SchemaFilter + SchemaFilterProvider (see application.yml)
@Entity
@Table(name = "application_user")
data class InvalidUser(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,
        val email: String = "",
        val creationDate: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC),
        val fake: Int = 42
)