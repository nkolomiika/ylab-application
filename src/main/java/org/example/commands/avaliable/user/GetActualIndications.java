package org.example.commands.avaliable.user;

import org.example.commands.abstracts.Command;
import org.example.commands.abstracts.Status;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.EmptyIndicationException;
import org.example.managers.IndicationsManager;
import org.example.model.data.Role;

/**
 * Класс, представляющий команду для получения актуальных показаний счетчиков.
 */
public class GetActualIndications extends Command {

    private IndicationsManager indicationsManager;

    /**
     * Конструктор для класса GetActualIndications.
     *
     * @param indicationsManager менеджер показаний, используемый для получения актуальных показаний счетчиков.
     */
    public GetActualIndications(IndicationsManager indicationsManager) {
        super(Role.USER,"get_actual_indications", "просмотр актуальных показаний счетчиков");
        this.indicationsManager = indicationsManager;
    }

    /**
     * Выполняет запрос на получение актуальных показаний счетчиков.
     *
     * @param request входящий запрос, содержащий информацию о пользователе.
     * @return Ответ, содержащий результат операции получения актуальных показаний и их состояние (статус).
     */
    @Override
    public Response execute(Request request) {
        try {
            return new Response(
                    indicationsManager.lastIndicationToString(request.getUser()),
                    Status.OK
            );
        } catch (EmptyIndicationException exception){
            return new Response("Показания счетчиков пусты", Status.ERROR);
        }

    }
}
