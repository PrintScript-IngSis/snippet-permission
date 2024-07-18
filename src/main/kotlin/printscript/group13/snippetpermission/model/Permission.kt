package printscript.group13.snippetpermission.model

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name= "permission")
data class Permission(
    @Column(name = "userId", nullable = false)
    val userId: String? = null,
    @Column(name = "snippetId", nullable = false)
    val snippetId: UUID? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "permission", nullable = false)
    val permission: PermissionType? = PermissionType.READ,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
)
