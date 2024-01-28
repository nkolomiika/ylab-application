package org.example.commands.avaliable.admin;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.model.data.Role;
import org.example.utils.logger.Logger;

/**
 * Класс, представляющий команду для просмотра аудита системы (логов).
 */
public class LoggerCommand extends Command {

    /**
     * Конструктор для класса LoggerCommand.
     * Устанавливает права доступа и описание команды.
     */
    public LoggerCommand() {
        super(Role.ADMIN, "logs", "просмотр аудита системы");
    }

    /**
     * Выполняет запрос просмотра аудита системы.
     * @param request входящий запрос, возможно, содержащий дополнительные данные для запроса аудита.
     * @return Ответ, содержащий результат просмотра аудита системы и его состояние (статус).
     */
    @Override
    public Response execute(Request request) {
        return new Response(
                Logger.getAllLogs(),
                Status.OK
        );
    }
}
