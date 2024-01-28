package org.example.utils.input;

/**
 * Функциональный интерфейс, предоставляющий метод для получения строки ввода.
 */
@FunctionalInterface
public interface UserInput {
    /**
     * Получает строку текста, введенную пользователем.
     * @return введенная пользователем строка текста
     */
    String nextLine();
}
