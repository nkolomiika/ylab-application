package org.example.commands.avaliable.auth;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.NoSuchUserException;
import org.example.managers.UserManager;
import org.example.model.User;
import org.example.utils.forms.UserDataForm;
import org.example.utils.input.ConsoleInput;
import org.example.utils.input.UserInput;

public class Login extends Command {

    private final UserManager userManager;

    public Login(UserManager userManager) {
        super("login", "вход в аккаунт");
        this.userManager = userManager;
    }

    @Override
    public Response execute(Request request)  {
        UserInput in = new ConsoleInput();
        try {
            User user = userManager.containsUser(UserDataForm.buildCredential(in, false));
            return new Response(
                    user,
                    "Вы успешно зашли в аккаунт",
                    Status.OK
            );
        } catch (NoSuchUserException exception) {
            return new Response(
                    "Неверный логин или пароль",
                    Status.ERROR
            );
        }
    }
}
