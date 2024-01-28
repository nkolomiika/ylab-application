package org.example.commands.avaliable.admin;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.managers.IndicationsManager;
import org.example.model.data.Role;

/**
 * Класс, представляющий команду для создания нового счетчика.
 */
public class CreateIndication extends Command {

    private IndicationsManager indicationsManager;

    /**
     * Конструктор для класса CreateIndication.
     *
     * @param indicationsManager менеджер показаний, используемый для добавления нового счетчика.
     */
    public CreateIndication(IndicationsManager indicationsManager) {
        super(Role.ADMIN, "create_indication", "добавить новый счетчик");
        this.indicationsManager = indicationsManager;
    }

    /**
     * Выполняет создание нового счетчика на основе запроса.
     *
     * @param request входящий запрос, возможно, содержащий дополнительные данные для создания счетчика.
     * @return Ответ, описывающий результат операции создания счетчика и его состояние (статус).
     */
    @Override
    public Response execute(Request request) {
        return new Response(
                "Индикатор " + this.indicationsManager.addIndication() + " добавлен",
                Status.OK
        );
    }
}
