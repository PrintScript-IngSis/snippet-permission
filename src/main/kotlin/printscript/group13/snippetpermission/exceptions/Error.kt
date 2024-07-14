package printscript.group13.snippetpermission.exceptions

class PermissionNotFoundException() : Exception() {
    override val message: String
        get() = "Permission not found"
}

class PermissionAlreadyExistsException() : Exception() {
    override val message: String
        get() = "Permission already exists"
}

class PermissionInvalidException(private val value: String) : RuntimeException() {
    override val message: String
        get() = "Permission $value is invalid"
}
