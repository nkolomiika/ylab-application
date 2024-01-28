package org.example.commands.avaliable.admin;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.NoSuchUserException;
import org.example.managers.UserManager;
import org.example.model.data.Role;

/**
 * Класс, представляющий команду для просмотра информации о конкретном пользователе.
 */
public class SearchUser extends Command {

    private UserManager userManager;

    /**
     * Конструктор для класса SearchUser.
     *
     * @param userManager менеджер пользователей, используемый для поиска информации о пользователе.
     */
    public SearchUser(UserManager userManager) {
        super(Role.ADMIN, "search_user", "просмотр информации о конкретном пользователе");
        this.userManager = userManager;
    }

    /**
     * Выполняет запрос просмотра информации о конкретном пользователе.
     *
     * @param request входящий запрос, возможно, содержащий дополнительные данные для запроса информации.
     * @return Ответ, содержащий результат просмотра информации о пользователе и его состояние (статус).
     */
    @Override
    public Response execute(Request request)  {
        try {
            return new Response(
                    this.userManager.printUser(this.userManager.searchUser()),
                    Status.OK
            );
        } catch (NoSuchUserException exception){
            return new Response("Пользователя с таким логином не существует", Status.ERROR);
        }
    }
}
