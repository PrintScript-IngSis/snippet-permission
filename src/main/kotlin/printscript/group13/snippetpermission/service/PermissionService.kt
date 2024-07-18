package printscript.group13.snippetpermission.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import printscript.group13.snippetpermission.exceptions.PermissionAlreadyExistsException
import printscript.group13.snippetpermission.exceptions.PermissionNotFoundException
import printscript.group13.snippetpermission.input.PermissionInput
import printscript.group13.snippetpermission.model.Permission
import printscript.group13.snippetpermission.model.PermissionType
import printscript.group13.snippetpermission.repository.PermissionRepository
import java.util.UUID

@Service
class PermissionService(@Autowired private val permissionRepository: PermissionRepository) {
    private val logger = LoggerFactory.getLogger(PermissionService::class.java)

    fun createPermission(userId: String, snippetId: UUID, permissionInput: PermissionInput): Permission {
        logger.info("Creating permission for user $userId and snippet $snippetId")
        val preexistingPermission = permissionRepository.findByUserIdAndSnippetId(userId, snippetId)
        if (preexistingPermission != null) {
            logger.error("Permission already exists for user $userId and snippet $snippetId")
            throw PermissionAlreadyExistsException()
        }
        val permission = Permission(
            userId = userId,
            snippetId = snippetId,
            permission = permissionInput.permission,
        )
        logger.info("Permission created for user $userId and snippet $snippetId")
        return permissionRepository.save(permission)
    }

    fun getPermissionBySnippetId(snippetId: UUID, userId: String): Permission? {
        logger.info("Getting permission for snippet $snippetId and user $userId")
        return permissionRepository.findByUserIdAndSnippetId(userId, snippetId) ?: throw PermissionNotFoundException()
    }

    fun getPermissionsByUserId(userId: String): List<Permission> {
        logger.info("Getting permissions for user $userId")
        return permissionRepository.findAllByUserId(userId)
    }

    fun updatePermission(snippetId: UUID, userId: String, permission: PermissionType): Permission {
        logger.info("Updating permission for snippet $snippetId and user $userId")
        val preexistingPermission = permissionRepository.findByUserIdAndSnippetId(userId, snippetId)
        if (preexistingPermission == null) {
            logger.error("Permission not found for snippet $snippetId and user $userId")
            throw PermissionNotFoundException()
        }
        return permissionRepository.updatePermission(preexistingPermission.id, permission)
    }

    fun deletePermissionsForSnippetId(snippetId: UUID) {
        logger.info("Deleting permissions for snippet $snippetId")
        permissionRepository.deleteAll(permissionRepository.findAllBySnippetId(snippetId))
    }
}
