package com.ems.backend.model.security;

public enum EmsRole {
    ROLE_USER ("user"),
    ROLE_MODERATOR ("mod"),
    ROLE_ADMIN ("admin"),
    ROLE_GROUP_ADMIN ("group_admin"),
    ROLE_INSTRUCTOR ("instructor");

    private final String role;

    private EmsRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static EmsRole valueOfLabel(String label) {
        for (EmsRole e : values()) {
            if (e.role.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
