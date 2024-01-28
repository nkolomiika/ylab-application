package org.example.commands.avaliable.admin;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.NoSuchUserException;
import org.example.managers.UserManager;
import org.example.model.data.Role;

/**
 * Класс, представляющий команду для повышения пользователя до статуса администратора.
 */
public class BoostUser extends Command {

    private UserManager userManager;

    /**
     * Конструктор для класса BoostUser.
     *
     * @param userManager менеджер пользователей, используемый для выполнения операции повышения.
     */
    public BoostUser(UserManager userManager) {
        super(Role.ADMIN, "boost", "повышает права пользователя до админа");
        this.userManager = userManager;
    }

    /**
     * Выполняет повышение пользователя до статуса администратора на основе запроса.
     *
     * @param request входящий запрос, необходимый для выполнения операции.
     * @return Ответ, описывающий результат операции повышения пользователя, и его состояние (статус).
     */
    @Override
    public Response execute(Request request) {
        try {
            return new Response("Пользователь "
                    + this.userManager.boostUser().getCredential().getLogin()
                    + " получил права админа", Status.OK);
        } catch (NoSuchUserException exception) {
            return new Response("Пользователя с таким логином не существует", Status.ERROR);
        }
    }
}
