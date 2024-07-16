package printscript.group13.snippetpermission.repository

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import printscript.group13.snippetpermission.model.Permission
import printscript.group13.snippetpermission.model.PermissionType
import java.util.UUID

interface PermissionRepository : JpaRepository<Permission, UUID> {
    fun findByUserIdAndSnippetId(
        userId: String,
        snippetId: UUID,
    ): Permission?

    @Modifying
    @Transactional
    @Query("UPDATE Permission p SET p.permission = :permission WHERE p.id = :id")
    fun updatePermission(
        id: UUID,
        permission: PermissionType,
    ): Permission

    fun findAllByUserId(userId: String): List<Permission>

    fun findAllBySnippetId(snippetId: UUID): List<Permission>
}
