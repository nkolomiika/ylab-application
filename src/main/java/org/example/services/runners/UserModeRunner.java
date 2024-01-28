package org.example.services.runners;

import lombok.AllArgsConstructor;
import org.example.commands.abstracts.Status;
import org.example.commands.avaliable.Exit;
import org.example.commands.avaliable.Help;
import org.example.commands.avaliable.user.AddIndications;
import org.example.commands.avaliable.user.CheckIndications;
import org.example.commands.avaliable.user.GetActualIndications;
import org.example.commands.avaliable.user.History;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.ExitObligedException;
import org.example.exceptions.NoSuchCommandException;
import org.example.managers.CommandManager;
import org.example.managers.IndicationsManager;
import org.example.model.User;
import org.example.utils.console.Console;
import org.example.utils.input.ConsoleInput;
import org.example.utils.input.UserInput;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

public class UserModeRunner implements Runner {

    private final CommandManager commandManager;
    private final Console console = new Console();
    private IndicationsManager indicationsManager;

    public UserModeRunner(IndicationsManager indicationsManager) {
        this.indicationsManager = indicationsManager;
        this.commandManager = new CommandManager();
        this.commandManager.addAllCommands(
                List.of(
                        new Exit(),
                        new GetActualIndications(indicationsManager),
                        new AddIndications(indicationsManager),
                        new CheckIndications(indicationsManager),
                        new History(indicationsManager)
                )
        );
        this.commandManager.addCommand(new Help(this.commandManager));
    }

    @Override
    public void run(UserInput in, User user) throws ExitObligedException {
        Response response;
        while (true) {
            String command;
            try {
                command = in.nextLine().trim();

                if (!command.equals("")) {
                    response = commandManager.executeCommand(new Request(user, command));
                    Status status = response.getStatus();
                    if (status.equals(Status.EXIT)) throw new ExitObligedException();
                    if (status.equals(Status.ERROR)) console.printError(response.getMessage());
                    else console.println(response.getMessage());
                    console.printBorder();
                }
            } catch (IllegalArgumentException | NoSuchElementException ignored) {
            } catch (NoSuchCommandException exception) {
                console.printError("Неверно введенная команда");
                console.printBorder();
            }
        }

    }
}
