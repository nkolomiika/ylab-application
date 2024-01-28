package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.model.User;

/**
 * Представляет запрос от пользователя.
 */
@Data
@AllArgsConstructor
public class Request {
    private User user;
    private String command;

    /**
     * Создает запрос с указанной командой.
     *
     * @param command строка, представляющая команду
     */
    public Request(String command) {
        this.command = command;
    }
}
