package org.example.commands.avaliable.user;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.EmptyIndicationException;
import org.example.managers.IndicationsManager;
import org.example.model.data.Role;

/**
 * Класс, представляющий команду для просмотра истории подачи показаний счетчиков.
 */
public class History extends Command {

    private IndicationsManager indicationsManager;

    /**
     * Конструктор для класса History.
     *
     * @param indicationsManager менеджер показаний, используемый для просмотра истории подачи показаний.
     */
    public History(IndicationsManager indicationsManager) {
        super(Role.USER,"history", "просмотр истории подачи показаний счетчиков");
        this.indicationsManager = indicationsManager;
    }

    /**
     * Выполняет запрос на получение истории подачи показаний счетчиков.
     *
     * @param request входящий запрос, содержащий информацию о пользователе.
     * @return Ответ, содержащий результат операции получения истории подачи показаний и его состояние (статус).
     */
    @Override
    public Response execute(Request request) {
        try {
            return new Response(this.indicationsManager.getHistoryOfUser(request.getUser()), Status.OK);
        } catch (EmptyIndicationException exception) {
            return new Response("История пуста", Status.ERROR);
        }
    }
}
