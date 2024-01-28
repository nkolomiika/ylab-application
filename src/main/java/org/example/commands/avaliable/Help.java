package org.example.commands.avaliable;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.managers.CommandManager;
import org.example.model.data.Role;

/**
 * Класс, представляющий команду для вывода информации по всем командам.
 */
public class Help extends Command {

    private final CommandManager commandManager;

    /**
     * Конструктор для класса Help.
     * Устанавливает права доступа и описание команды, а также принимает менеджер команд в качестве параметра.
     *
     * @param commandManager менеджер команд, используемый для получения информации по командам.
     */
    public Help(CommandManager commandManager) {
        super(Role.ALL, "help", "вывод информации по всем командам");
        this.commandManager = commandManager;
    }

    /**
     * Выполняет запрос на вывод информации по всем командам на основе входящего запроса.
     * Если пользователь не вошел в систему, возвращает информацию о стандартных командах.
     * В противном случае возвращает информацию о доступных командах для пользовательской роли.
     *
     * @param request входящий запрос, содержащий информацию о пользователе и его сессионной роли.
     * @return Ответ, содержащий информацию по командам и их состояние (статус).
     */
    @Override
    public Response execute(Request request) {
        if (request.getUser() == null)
            return new Response(
                    commandManager.getStarterCommands(),
                    Status.OK
            );
        return new Response(
                commandManager.getAllCommands(request.getUser().getSessionRole()),
                Status.OK
        );
    }
}
