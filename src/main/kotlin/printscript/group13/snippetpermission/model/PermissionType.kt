package printscript.group13.snippetpermission.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import printscript.group13.snippetpermission.exceptions.PermissionInvalidException

enum class PermissionType(val value: String) {
    READ("read"),
    READ_WRITE("read:write"),
    OWNER("owner"),
    ;

    @JsonValue
    fun toValue(): String {
        return this.value
    }

    companion object {
        @JsonCreator
        @JvmStatic
        fun fromValue(value: String): PermissionType {
            return entries.firstOrNull { it.value == value }
                ?: throw PermissionInvalidException(value)
        }
    }
}
