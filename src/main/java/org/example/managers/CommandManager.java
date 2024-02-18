package org.example.managers;

import org.example.commands.abstracts.Command;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.exceptions.NoSuchCommandException;
import org.example.model.data.Role;
import org.example.utils.console.Console;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Менеджер команд отвечает за обработку и выполнение команд пользователей.
 */
public class CommandManager {

    private HashMap<String, Command> commands;
    private final Console console = new Console();

    /**
     * Создает новый менеджер команд.
     */
    public CommandManager() {
        this.commands = new HashMap<>();
    }

    /**
     * Добавляет все команды в коллекцию.
     *
     * @param commands коллекция команд
     */
    public void addAllCommands(Collection<Command> commands) {
        this.commands.putAll(
                commands.stream()
                        .collect(Collectors.toMap(Command::getName, s -> s))
        );
    }

    /**
     * Добавляет команду в менеджер.
     *
     * @param command команда для добавления
     */
    public void addCommand(Command command) {
        this.commands.put(command.getName(), command);
    }

    /**
     * Выполняет команду на основе полученного запроса.
     *
     * @param request запрос на выполнение команды
     * @return результат выполнения команды в виде ответа
     * @throws NoSuchCommandException если команда не существует
     */
    public Response executeCommand(Request request) {
        String command = request.getCommand();
        if (this.commands.containsKey(command)) {
            var exec = commands.get(command);
            var execRole = exec.getRole();
            if (execRole.equals(Role.ALL)
                    || execRole.equals(Role.NON_AUTH))
                return exec.execute(request);

            if (exec.getRole().equals(request.getUser().getSessionRole()))
                return exec.execute(request);

        }
        throw new
                NoSuchCommandException();

    }

    /**
     * Получает список всех команд для указанной роли.
     *
     * @param role роль пользователя
     * @return список всех команд для указанной роли
     */
    public String getAllCommands(Role role) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            var commandRole = entry.getValue().getRole();
            if (commandRole.equals(role) || commandRole.equals(Role.ALL)) {
                sb.append(i).append(". ").append(entry.getValue().toString()).append("\n");
                i++;
            }
        }

        return sb.reverse().replace(0, 1, "").reverse().toString();
    }

    /**
     * Получает список всех стартовых команд.
     *
     * @return список всех стартовых команд
     */
    public String getStarterCommands() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            sb.append(i).append(". ").append(entry.getValue().toString()).append("\n");
            i++;

        }

        return sb.reverse().replace(0, 1, "").reverse().toString();
    }

    /**
     * Проверяет, содержит ли менеджер указанную команду.
     *
     * @param command проверяемая команда
     * @return true, если менеджер содержит указанную команду, в противном случае - false
     */
    public boolean containsCommand(String command) {
        return this.commands.containsKey(command);
    }
}
