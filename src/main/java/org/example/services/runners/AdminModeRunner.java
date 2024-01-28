package org.example.services.runners;

import org.example.commands.abstracts.Status;
import org.example.commands.avaliable.Exit;
import org.example.commands.avaliable.Help;
import org.example.commands.avaliable.admin.AddIndication;
import org.example.commands.avaliable.admin.BoostUser;
import org.example.commands.avaliable.admin.InfoAboutUsers;
import org.example.commands.avaliable.admin.SearchUser;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.ExitObligedException;
import org.example.exceptions.NoSuchCommandException;
import org.example.managers.CommandManager;
import org.example.managers.IndicationsManager;
import org.example.managers.UserManager;
import org.example.model.User;
import org.example.utils.console.Console;
import org.example.utils.input.UserInput;

import java.util.List;
import java.util.NoSuchElementException;

public class AdminModeRunner implements Runner {

    private final CommandManager commandManager;
    private final Console console = new Console();
    private UserManager userManager;
    private IndicationsManager indicationsManager;

    public AdminModeRunner(UserManager userManager, IndicationsManager indicationsManager) {
        this.commandManager = new CommandManager();
        this.indicationsManager = indicationsManager;
        this.userManager = userManager;
        this.commandManager.addAllCommands(
                List.of(
                        new Exit(),
                        new BoostUser(userManager),
                        new InfoAboutUsers(userManager),
                        new SearchUser(userManager),
                        new AddIndication(indicationsManager)
                )
        );
        this.commandManager.addCommand(new Help(this.commandManager));
    }

    @Override
    public void run(UserInput in, User user) throws ExitObligedException {
        console.printError("Теперь вы админ!");
        console.printBorder();

        Response response;
        while (true) {
            String command;
            try {
                command = in.nextLine().trim();

                if (!command.equals("")) {
                    response = commandManager.executeCommand(new Request(command));
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
