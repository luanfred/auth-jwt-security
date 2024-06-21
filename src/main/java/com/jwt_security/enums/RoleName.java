package com.jwt_security.enums;

public enum RoleName {
    ADMIN("admin"),
    USER("user");

    private final String roleName;
    RoleName(String roleName) {
        this.roleName = roleName;
    }

    public String roleName() {
        return this.roleName;
    }
}
