package printscript.group13.snippetpermission.input

import org.jetbrains.annotations.NotNull
import printscript.group13.snippetpermission.model.PermissionType

data class PermissionInput(
    @field:NotNull("property 'permission' cannot be null")
    val permission: PermissionType,
)
