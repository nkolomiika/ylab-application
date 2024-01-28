package org.example.commands.avaliable.auth;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.managers.UserManager;
import org.example.model.User;
import org.example.model.data.Credential;
import org.example.model.data.Role;
import org.example.utils.forms.UserDataForm;
import org.example.utils.input.ConsoleInput;
import org.example.utils.input.UserInput;

public class Register extends Command {

    private final UserManager userManager;

    public Register(UserManager userManager) {
        super("register", "регистрация нового пользователя");
        this.userManager = userManager;
    }

    @Override
    public Response execute(Request request) {
        UserInput in = new ConsoleInput();
        Credential creds = UserDataForm.buildCredential(in, true);

        if (userManager.checkLogin(creds.getLogin()))
            return new Response(
                    "Пользователь с таким логином уже существует",
                    Status.ERROR);

        User user = new User(
                creds,
                UserDataForm.buildSNP(in),
                UserDataForm.buildAddress(in),
                Role.USER
        );

        userManager.addUser(user);
        return new Response(
                user,
                "Пользователь успешно зарегистрирован",
                Status.OK
        );
    }
}
