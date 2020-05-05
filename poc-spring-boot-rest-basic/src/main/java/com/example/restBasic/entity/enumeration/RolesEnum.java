package com.example.restBasic.entity.enumeration;

import lombok.Getter;

@Getter
public enum RolesEnum {
    USER("USER", 1L, "ROLE_USER"),
    ADMIN("ADMIN", 2L, "ROLE_ADMIN");

    private String tag;
    private Long id;
    private String role;

    RolesEnum(String tag, Long id, String role) {
        this.id = id;
        this.tag = tag;
        this.role = role;
    }

    public static RolesEnum findTag(String tag) {
        for (RolesEnum roles: RolesEnum.values()) {
            if (tag == roles.getTag()) return roles;
        }
        throw new IllegalArgumentException("Tag invalido");
    }

    public static RolesEnum findId(Long id) {
        for (RolesEnum roles: RolesEnum.values()) {
            if (id == roles.getId()) return roles;
        }
        throw new IllegalArgumentException("Id invalido");
    }

    public static RolesEnum findRole(String role) {
        for (RolesEnum roles: RolesEnum.values()) {
            if (role == roles.role) return roles;
        }
        throw new IllegalArgumentException("Role invalido");
    }
}
