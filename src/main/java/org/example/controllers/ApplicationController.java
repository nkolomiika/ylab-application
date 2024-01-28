package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.exceptions.ExitObligedException;
import org.example.exceptions.NoSuchCommandException;
import org.example.model.User;
import org.example.services.AuthService;
import org.example.services.RuntimeService;
import org.example.utils.console.Console;
import org.example.utils.input.UserInput;

@AllArgsConstructor
public class ApplicationController implements Controller {

    private final Console console = new Console();
    private AuthService authService;
    private RuntimeService runtimeService;

    public User run(UserInput in) {
        /*
         todo
          - проработать логику контроллера работы(это основной контроллер)
          - придумать админа и его панель, что происходит, если авторизуется админ
          - при выходе сделать панель для авторизации
          - *придумать как красиво оформить консоль*
         */
        User authUser;
        while (true) {
            while (true) {
                try {
                    authUser = authService.run(in);
                    break;
                } catch (NoSuchCommandException exception) {
                    console.printError("Неверно введенная команда");
                }
            }
            while (true) {
                try {
                    runtimeService.run(in, authUser);
                } catch (NoSuchCommandException exception) {
                    console.printError("Неверно введенная команда");
                } catch (ExitObligedException exception) {
                    console.println("До свидания!");
                    break;
                }
            }
        }
    }
}

