package org.example.commands.avaliable.auth;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.NoSuchUserException;
import org.example.managers.UserManager;
import org.example.model.User;
import org.example.model.data.Role;
import org.example.utils.forms.UserDataForm;

/**
 * Класс, представляющий команду для входа в аккаунт.
 */
public class Login extends Command {
    private final UserManager userManager;

    /**
     * Конструктор для класса Login.
     *
     * @param userManager менеджер пользователей, используемый для выполнения входа в аккаунт.
     */
    public Login(UserManager userManager) {
        super(Role.NON_AUTH, "login", "вход в аккаунт");
        this.userManager = userManager;
    }

    /**
     * Выполняет запрос на вход в аккаунт.
     *
     * @param request входящий запрос, возможно, содержащий данные для входа.
     * @return Ответ, содержащий результат операции входа и его состояние (статус).
     */
    @Override
    public Response execute(Request request) {
        try {
            User user = userManager.containsUser(UserDataForm.buildCredential(false));
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
