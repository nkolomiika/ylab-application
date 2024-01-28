package org.example.services;

import lombok.AllArgsConstructor;
import org.example.exceptions.ExitObligedException;
import org.example.exceptions.NoSuchCommandException;
import org.example.model.User;
import org.example.model.data.Role;
import org.example.services.runners.InteractiveModeRunner;
import org.example.utils.console.Console;
import org.example.utils.input.UserInput;

import static org.example.model.data.Role.ALL;
import static org.example.model.data.Role.NON_AUTH;

@AllArgsConstructor
public class RuntimeService {

    private final Console console = new Console();
    private InteractiveModeRunner runner;

    public void run(UserInput in, User user) throws NoSuchCommandException, ExitObligedException {
        if (user.getRole().equals(Role.ADMIN))
            user.setSessionRole(this.askAdmin(in));

        this.runner.run(in, user, user.getSessionRole());
    }

    private Role askAdmin(UserInput in) {
        String command;
        boolean flag = true;

        while (true) {
            try {
                if (flag) console.print(printRoles());
                command = in.nextLine().trim();
                if (command.equals("")) flag = false;

                return Role.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException exception) {
                console.printError("Неизвестная роль");
            }
        }
    }

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
