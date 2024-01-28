package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.commands.abstracts.Status;
import org.example.exceptions.ExitObligedException;
import org.example.exceptions.NoSuchCommandException;
import org.example.model.User;
import org.example.services.AuthService;
import org.example.services.RuntimeService;
import org.example.utils.console.Console;
import org.example.utils.input.UserInput;
import org.example.utils.logger.Logger;

/**
 * Класс ApplicationController отвечает за управление потоком приложения.
 */
@AllArgsConstructor
public class ApplicationController implements Controller {

    private final Console console = new Console();
    private AuthService authService;
    private RuntimeService runtimeService;

    /**
     * Запускает приложение на основе предоставленного пользовательского ввода.
     *
     * @param in пользовательский ввод
     */
    public void run(UserInput in) {
        User authUser;
        while (true) {
            while (true) {
                try {
                    authUser = authService.run(in);
                    break;
                } catch (NoSuchCommandException exception) {
                    console.printError("Введена неверная команда");
                }
            }
            while (true) {
                try {
                    runtimeService.run(in, authUser);
                } catch (NoSuchCommandException exception) {
                    console.printError("Введена неверная команда");
                } catch (ExitObligedException exception) {
                    Logger.addLog(
                            Logger.createLogFormatString(
                                    authUser.getCredential().getLogin(),
                                    "exit",
                                    Status.OK));

                    console.println("До свидания!");
                    break;
                }
            }
        }
    }
}
