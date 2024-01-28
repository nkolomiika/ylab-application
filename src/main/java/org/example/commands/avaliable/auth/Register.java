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

/**
 * Класс, представляющий команду для регистрации нового пользователя.
 */
public class Register extends Command {

    private final UserManager userManager;

    /**
     * Конструктор для класса Register.
     *
     * @param userManager менеджер пользователей, используемый для регистрации нового пользователя.
     */
    public Register(UserManager userManager) {
        super(Role.NON_AUTH, "register", "регистрация нового пользователя");
        this.userManager = userManager;
    }

    /**
     * Выполняет запрос на регистрацию нового пользователя.
     *
     * @param request входящий запрос, возможно, содержащий данные для регистрации нового пользователя.
     * @return Ответ, содержащий результат операции регистрации и его состояние (статус).
     */
    @Override
    public Response execute(Request request) {
        Credential creds = UserDataForm.buildCredential(true);

        if (userManager.checkLogin(creds.getLogin()))
            return new Response(
                    "Пользователь с таким логином уже существует",
                    Status.ERROR);

        User user = new User(
                creds,
                UserDataForm.buildSNP(),
                UserDataForm.buildAddress(),
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
