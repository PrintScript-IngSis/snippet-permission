package printscript.group13.snippetpermission.controller

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import printscript.group13.snippetpermission.input.PermissionInput
import printscript.group13.snippetpermission.model.Permission
import printscript.group13.snippetpermission.service.PermissionService
import java.util.UUID

@RestController
@RequestMapping("/api/permissions")
class PermissionController(
    @Autowired private val permissionService: PermissionService,
) {
    @PostMapping("/{snippetId}/users/{userId}")
    fun createPermission(
        @PathVariable snippetId: UUID,
        @PathVariable userId: String,
        @Valid @RequestBody permissionInput: PermissionInput,
    ): ResponseEntity<Permission> {
        val permission = permissionService.createPermission(userId, snippetId, permissionInput)
        return ResponseEntity.ok().body(permission)
    }

    @GetMapping("/{snippetId}/users/{userId}")
    fun getPermissionsBySnippetId(
        @PathVariable snippetId: UUID,
        @PathVariable userId: String,
    ): ResponseEntity<Permission> {
        val permissions = permissionService.getPermissionBySnippetId(snippetId, userId)
        return ResponseEntity.ok().body(permissions)
    }

    @PatchMapping("/{snippetId}/users/{userId}")
    fun updatePermission(
        @PathVariable snippetId: UUID,
        @PathVariable userId: String,
        @Valid @RequestBody permissionInput: PermissionInput,
    ): ResponseEntity<Permission> {
        val permission = permissionService.updatePermission(snippetId, userId, permissionInput.permission!!)
        return ResponseEntity.ok().body(permission)
    }

    @DeleteMapping("/{snippetId}")
    fun deletePermissionBySnippetId(
        @PathVariable snippetId: UUID,
    ): ResponseEntity<Unit> {
        permissionService.deletePermissionsForSnippetId(snippetId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/user/{userId}")
    fun getPermissionsByUserId(
        @PathVariable userId: String,
    ): ResponseEntity<List<Permission>> {
        val permissions = permissionService.getPermissionsByUserId(userId)
        return ResponseEntity.ok().body(permissions)
    }
}
