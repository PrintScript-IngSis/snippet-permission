package printscript.group13.snippetpermission

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import printscript.group13.snippetpermission.controller.PermissionController
import printscript.group13.snippetpermission.exceptions.PermissionNotFoundException
import printscript.group13.snippetpermission.input.PermissionInput
import printscript.group13.snippetpermission.model.PermissionType
import printscript.group13.snippetpermission.service.PermissionService
import java.util.UUID

@SpringBootTest
class SnippetPermissionApplicationTests {
    @Autowired
    private lateinit var permissionService: PermissionService

    @Autowired
    private lateinit var permissionController: PermissionController

    @Test
    fun contextLoads() {
    }
//    @Test
//    fun testCreatePermissionWithNoErrorsShouldMatch() {
//        val userId = "test"
//        val snippetId:UUID = UUID.randomUUID()
//        val permission = PermissionInput(PermissionType.READ)
//        val createdPermission = permissionService.createPermission(userId, snippetId, permission)
//        assert(createdPermission.userId == userId)
//        assert(createdPermission.snippetId == snippetId)
//        assert(createdPermission.permission == permission.permission)
//    }
//    @Test
//    fun testGetPermissionBySnippetIdWithNoErrorsShouldMatch() {
//        val userId = "test"
//        val snippetId:UUID = UUID.randomUUID()
//        val permission = PermissionInput(PermissionType.READ)
//        permissionService.createPermission(userId, snippetId, permission)
//        val retrievedPermission = permissionService.getPermissionBySnippetId(snippetId, userId)
//        assert(retrievedPermission.userId == userId)
//        assert(retrievedPermission.snippetId == snippetId)
//        assert(retrievedPermission.permission == permission.permission)
//    }
//    @Test
//    fun testUpdatePermissionWithNoErrorsShouldMatch() {
//        val userId = "test"
//        val snippetId:UUID = UUID.randomUUID()
//        val permission = PermissionInput(PermissionType.READ)
//        permissionService.createPermission(userId, snippetId, permission)
//        val updatedPermission = permissionService.updatePermission(snippetId, userId, PermissionType.READ_WRITE)
//        assert(updatedPermission.userId == userId)
//        assert(updatedPermission.snippetId == snippetId)
//        assert(updatedPermission.permission == PermissionType.READ_WRITE)
//    }
//    @Test
//    fun testDeletePermissionsByUserIdWithNoErrorsShouldMatch() {
//        val userId = "test"
//        val snippetId:UUID = UUID.randomUUID()
//        val permission = PermissionInput(PermissionType.READ)
//        permissionService.createPermission(userId, snippetId, permission)
//        permissionService.deletePermissionsForSnippetId(snippetId)
//        assertThrows<PermissionNotFoundException> { permissionService.getPermissionBySnippetId(snippetId,userId) }
//    }

    @Test
    fun testCreateControllerPermissionWithNoErrorsShouldMatch() {
        val userId = "test"
        val snippetId: UUID = UUID.randomUUID()
        val permission = PermissionInput(PermissionType.READ)
        val createdPermission = permissionController.createPermission(snippetId, userId, permission)
        assert(createdPermission.body!!.userId == userId)
        assert(createdPermission.body!!.snippetId == snippetId)
        assert(createdPermission.body!!.permission == permission.permission)
    }

    @Test
    fun testGetControllerPermissionBySnippetIdWithNoErrorsShouldMatch() {
        val userId = "test"
        val snippetId: UUID = UUID.randomUUID()
        val permission = PermissionInput(PermissionType.READ)
        permissionService.createPermission(userId, snippetId, permission)
        val retrievedPermission = permissionController.getPermissionsBySnippetId(snippetId, userId)
        assert(retrievedPermission.body!!.userId == userId)
        assert(retrievedPermission.body!!.snippetId == snippetId)
        assert(retrievedPermission.body!!.permission == permission.permission)
    }

    @Test
    fun testUpdateControllerPermissionWithNoErrorsShouldMatch() {
        val userId = "test"
        val snippetId: UUID = UUID.randomUUID()
        val permission = PermissionInput(PermissionType.READ)
        permissionService.createPermission(userId, snippetId, permission)
        val updatedPermission = permissionController.updatePermission(snippetId, userId, permission)
        assert(updatedPermission.body!!.userId == userId)
        assert(updatedPermission.body!!.snippetId == snippetId)
        assert(updatedPermission.body!!.permission == permission.permission)
    }

    @Test
    fun testDeleteControllerPermissionsByUserIdWithNoErrorsShouldMatch() {
        val userId = "test"
        val snippetId: UUID = UUID.randomUUID()
        val permission = PermissionInput(PermissionType.READ)
        permissionService.createPermission(userId, snippetId, permission)
        permissionController.deletePermissionBySnippetId(snippetId)
        assertThrows<PermissionNotFoundException> { permissionService.getPermissionBySnippetId(snippetId, userId) }
    }

    @Test
    fun testGetPermissionsByUserIdWithNoErrorsShouldMatch() {
        val userId = "test"
        val snippetId: UUID = UUID.randomUUID()
        val permission = PermissionInput(PermissionType.READ)
        permissionService.createPermission(userId, snippetId, permission)
        val retrievedPermissions = permissionController.getPermissionsByUserId(userId)
        assert(retrievedPermissions.body!!.size == 1)
        assert(retrievedPermissions.body!![0].userId == userId)
        assert(retrievedPermissions.body!![0].snippetId == snippetId)
        assert(retrievedPermissions.body!![0].permission == permission.permission)
    }
}
