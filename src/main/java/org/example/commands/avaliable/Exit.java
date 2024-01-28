package org.example.commands.avaliable;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.model.data.Role;

/**
 * Класс, представляющий команду для выхода из аккаунта.
 */
public class Exit extends Command {

    /**
     * Конструктор для класса Exit.
     * Устанавливает права доступа и описание команды.
     */
    public Exit() {
        super(Role.ALL,"exit", "выход из аккаунта");
    }

    /**
     * Выполняет запрос на выход из аккаунта.
     *
     * @param request входящий запрос, возможно, содержащий дополнительные данные для выхода.
     * @return Ответ, содержащий прощальное сообщение и состояние (статус) выхода.
     */
    @Override
    public Response execute(Request request) {
        return new Response("До скорой встречи!", Status.EXIT);
    }
}
