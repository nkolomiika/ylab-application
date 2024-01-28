package org.example.services;

import lombok.AllArgsConstructor;
import org.example.commands.abstracts.Status;
import org.example.commands.avaliable.Help;
import org.example.commands.avaliable.auth.Login;
import org.example.commands.avaliable.auth.Register;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.NoSuchCommandException;
import org.example.exceptions.NoSuchUserException;
import org.example.managers.CommandManager;
import org.example.managers.UserManager;
import org.example.model.User;
import org.example.model.data.Role;
import org.example.utils.console.Console;
import org.example.utils.input.UserInput;
import org.example.utils.logger.Logger;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class AuthService {

    private final Console console = new Console();
    private final CommandManager commandManager;

    public AuthService(UserManager userManager) {
        this.commandManager = new CommandManager();
        commandManager.addAllCommands(
                List.of(
                        new Login(userManager),
                        new Register(userManager)
                )
        );
        commandManager.addCommand(new Help(this.commandManager));
    }

    public User run(UserInput in) throws NoSuchCommandException {
        Response response;
        console.printBorder();
        console.println(this.commandManager.executeCommand(new Request("help")).getMessage());
        console.printBorder();
        while (true) {
            try {
                String command = in.nextLine().trim().toLowerCase();

                if (!command.equals("")) {

                    response = commandManager.executeCommand(new Request(command));
                    if (response.getStatus().equals(Status.ERROR)) console.printError(response.getMessage());
                    else console.println(response.getMessage());

                    if (response.getUser() == null) {
                        console.printBorder();
                        Logger.addLog(
                                Logger.createLogString(Role.NON_AUTH.toString(), command, response.getStatus())
                        );
                    }

                    if (response.getUser() != null) {
                        var user = response.getUser();
                        Logger.addLog(
                                Logger.createLogString(user.getCredential().getLogin(), command, response.getStatus())
                        );
                        return user;
                    }
                }
            } catch (IllegalArgumentException | NoSuchElementException ignored) {
            }
        }
    }
}