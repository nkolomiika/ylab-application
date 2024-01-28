package org.example.model.data;

import org.example.exceptions.NoSuchRoleException;

import java.util.Arrays;

public enum Role {
    USER,
    ADMIN,
    ALL, NON_AUTH;

    /**
     * Создает строковое представление ролей.
     *
     * @return Строковое представление ролей
     */
    public static String printRoles() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Role role : Role.values()) {
            if (role.equals(ALL) || role.equals(NON_AUTH))
                continue;
            i++;
            sb.append(i).append(". ").append(role).append("\n");
        }
        return sb.toString();
    }
}
