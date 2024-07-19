package printscript.group13.snippetpermission.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class PermissionNotFoundException() : RuntimeException("Permission not found")

@ResponseStatus(HttpStatus.CONFLICT)
class PermissionAlreadyExistsException() : RuntimeException("Permission already exists")

@ResponseStatus(HttpStatus.BAD_REQUEST)
class PermissionInvalidException(value: String) : RuntimeException("Permission $value is invalid")

//nada que pushear
