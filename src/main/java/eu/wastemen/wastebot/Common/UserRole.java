package eu.wastemen.wastebot.Common;

import lombok.EqualsAndHashCode;
import lombok.ToString;

public enum UserRole {

    REGULAR_USER(""),
    MODERATOR("roadman"),
    ADMINISTRATOR("supreme wasteman"),
    OWNER("head wasteman");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
