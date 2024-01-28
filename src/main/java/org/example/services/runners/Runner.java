package org.example.services.runners;

import org.example.exceptions.ExitObligedException;
import org.example.model.User;
import org.example.model.data.Role;
import org.example.utils.input.UserInput;

/**
 * Функциональный интерфейс, представляющий возможность запуска выполнения определённой задачи с использованием ввода от пользователя, информации о пользователе и их роли.
 */
@FunctionalInterface
public interface Runner {
    /**
     * Запуск выполнения задачи с использованием ввода от пользователя, информации о пользователе и их роли.
     *
     * @param in   Ввод от пользователя
     * @param user Текущий пользователь
     * @param role Роль пользователя
     * @throws ExitObligedException если выполнение задачи требует выхода
     */
    void run(UserInput in, User user, Role role) throws ExitObligedException;
}
