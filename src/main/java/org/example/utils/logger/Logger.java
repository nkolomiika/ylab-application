package org.example.utils.logger;

import org.example.commands.abstracts.Status;
import org.example.utils.console.Console;

/**
 * Класс Logger содержит методы для добавления, извлечения логов и создания строкового представления логов.
 */
public class Logger {
    private static final Console console = new Console(); // Экземпляр класса Console
    private static StringBuilder logs = new StringBuilder(); // Поле для хранения логов в виде строки

    /**
     * Добавляет лог в журнал.
     * @param l добавляемый лог
     */
    public static void addLog(String l) {
        logs.append(l).append("\n");
    }

    /**
     * Получает все логи в обратном порядке.
     * @return все логи в виде строки
     */
    public static String getAllLogs() {
        return logs.toString();
    }

    /**
     * Создает строковое представление лога.
     * @param user пользователь
     * @param command команда
     * @param result результат выполнения
     * @return строковое представление лога
     */
    public static String createLogFormatString(String user, String command, Status result){
        return user + " -> " + command + " : " + result;
    }
}
