package org.example.services;

import lombok.AllArgsConstructor;
import org.example.exceptions.ExitObligedException;
import org.example.exceptions.NoSuchCommandException;
import org.example.managers.IndicationsManager;
import org.example.model.User;
import org.example.model.data.Role;
import org.example.services.runners.AdminModeRunner;
import org.example.services.runners.UserModeRunner;
import org.example.utils.console.Console;
import org.example.utils.input.UserInput;

@AllArgsConstructor
public class RuntimeService {

    private final Console console = new Console();
    private AdminModeRunner adminRunner;
    private UserModeRunner userRunner;
    private final String VARIANTS = """
            Выберите роль
            1. ADMIN
            2. USER
            """;

    public void run(UserInput in, User user) throws NoSuchCommandException, ExitObligedException {
        boolean isAdmin = false;
        if (user.getRole().equals(Role.ADMIN)) isAdmin = this.askAdmin(in);
        if (isAdmin) this.adminRunner.run(in, user);
        else this.userRunner.run(in, user);
    }

    private boolean askAdmin(UserInput in) {
        String command;
        boolean flag = false;
        while (true) {
            if (!flag) console.print(VARIANTS);
            flag = false;
            command = in.nextLine().trim().toUpperCase();

            switch (command) {
                case "USER", "ADMIN" -> {
                    console.printBorder();
                    return command.equals(Role.ADMIN.toString());
                }
                case "" -> {
                    flag = true;
                }
                default -> {
                    throw new NoSuchCommandException();
                }
            }
        }
    }
}
