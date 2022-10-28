package eu.wastemen.wastebot.Common;


import lombok.Getter;
import lombok.ToString;

import java.util.EnumSet;
import java.util.Set;

@ToString
public enum CommandPermission {
    REGULAR(EnumSet.of(UserRole.REGULAR_USER, UserRole.MODERATOR, UserRole.ADMINISTRATOR, UserRole.OWNER)),
    MODERATOR(EnumSet.of(UserRole.MODERATOR, UserRole.ADMINISTRATOR, UserRole.OWNER)),
    ADMINISTRATOR(EnumSet.of(UserRole.ADMINISTRATOR, UserRole.OWNER)),
    OWNER(EnumSet.of(UserRole.OWNER));

    @Getter
    private Set<UserRole> userRoles;

    CommandPermission(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
