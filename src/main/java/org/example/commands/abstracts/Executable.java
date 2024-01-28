package org.example.commands.abstracts;

import org.example.dto.Request;
import org.example.dto.Response;

/**
 * Функциональный интерфейс, представляющий объект, способный выполнить определенное действие на основе входящего запроса и вернуть ответ.
 */
@FunctionalInterface
public interface Executable {

    /**
     * Выполняет действие на основе входящего запроса и возвращает соответствующий ответ.
     *
     * @param request {@link Request} входящий запрос, на основе которого должно быть выполнено действие.
     * @return Ответ на входящий запрос.
     */
    Response execute(Request request);
}
