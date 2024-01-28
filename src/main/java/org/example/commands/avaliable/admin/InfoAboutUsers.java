package org.example.commands.avaliable.admin;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.managers.UserManager;
import org.example.model.data.Role;

/**
 * Класс, представляющий команду для получения информации о всех пользователях.
 */
public class InfoAboutUsers extends Command {

    private UserManager userManager;

    /**
     * Конструктор для класса InfoAboutUsers.
     *
     * @param userManager менеджер пользователей, используемый для получения информации о пользователях.
     */
    public InfoAboutUsers(UserManager userManager) {
        super(Role.ADMIN, "info_about_all_users", "получить информацию о всех пользователях");
        this.userManager = userManager;
    }

    /**
     * Выполняет запрос информации о всех пользователях.
     *
     * @param request входящий запрос, возможно, содержащий дополнительные данные для запроса информации.
     * @return Ответ, содержащий информацию о пользователях и их состояние (статус).
     */
    @Override
    public Response execute(Request request) {
        return new Response(userManager.printAllUsers(), Status.OK);
    }
}
