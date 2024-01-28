package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.commands.abstracts.Status;
import org.example.model.User;

/**
 * Представляет ответ на запрос от пользователя.
 */
@Data
@AllArgsConstructor
public class Response {

    private User user;
    private String message;
    private Status status;

    /**
     * Создает ответ с указанным сообщением и статусом.
     *
     * @param message сообщение в ответ на запрос
     * @param status  статус выполнения запроса
     */
    public Response(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    /**
     * Создает ответ с указанным статусом и пользователем.
     *
     * @param status статус выполнения запроса
     * @param user   пользователь, к которому относится ответ
     */
    public Response(Status status, User user) {
        this.status = status;
        this.user = user;
    }
}
