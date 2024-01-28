package org.example.controllers;

import org.example.utils.input.UserInput;

/**
 * Интерфейс контроллера приложения.
 */
@FunctionalInterface
public interface Controller {
    /**
     * Запускает контроллер с указанным пользовательским вводом.
     *
     * @param in пользовательский ввод
     */
    void run(UserInput in);
}
