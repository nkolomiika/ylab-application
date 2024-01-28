package org.example.services.runners;

import org.example.commands.abstracts.Status;
import org.example.commands.avaliable.Exit;
import org.example.commands.avaliable.Help;
import org.example.commands.avaliable.admin.BoostUser;
import org.example.commands.avaliable.admin.CreateIndication;
import org.example.commands.avaliable.admin.InfoAboutUsers;
import org.example.commands.avaliable.admin.SearchUser;
import org.example.commands.avaliable.user.AddIndications;
import org.example.commands.avaliable.user.CheckIndications;
import org.example.commands.avaliable.user.GetActualIndications;
import org.example.commands.avaliable.user.History;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.ExitObligedException;
import org.example.exceptions.NoSuchCommandException;
import org.example.exceptions.NoSuchRoleException;
import org.example.managers.CommandManager;
import org.example.managers.IndicationsManager;
import org.example.managers.UserManager;
import org.example.model.User;
import org.example.model.data.Role;
import org.example.utils.console.Console;
import org.example.utils.input.UserInput;

import java.util.List;
import java.util.NoSuchElementException;

public class InteractiveModeRunner implements Runner {

    private final CommandManager commandManager;
    private final Console console = new Console();
    private IndicationsManager indicationsManager;
    private UserManager userManager;

    public InteractiveModeRunner(IndicationsManager indicationsManager, UserManager userManager) {
        this.indicationsManager = indicationsManager;
        this.userManager = userManager;
        this.commandManager = new CommandManager();
        this.commandManager.addAllCommands(
                List.of(
                        new Exit(),
                        new BoostUser(userManager),
                        new InfoAboutUsers(userManager),
                        new SearchUser(userManager),
                        new CreateIndication(indicationsManager),
                        new GetActualIndications(indicationsManager),
                        new AddIndications(indicationsManager),
                        new CheckIndications(indicationsManager),
                        new History(indicationsManager)
                )
        );
        this.commandManager.addCommand(new Help(this.commandManager));

    }

    @Override
    public void run(UserInput in, User user, Role role) throws ExitObligedException {
        console.printBorder();
        console.println(
                role.equals(Role.ADMIN)
                        ? "Теперь вы админ!"
                        : "Добро пожаловать, " + user.getSnp().getName() + "!");
        console.printBorder();
        console.println(this.commandManager.executeCommand(new Request(user, "help")).getMessage());
        console.printBorder();
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
            } catch (NoSuchRoleException exception) {
                console.printError("Неизвестная роль");
            }
        }
    }
}